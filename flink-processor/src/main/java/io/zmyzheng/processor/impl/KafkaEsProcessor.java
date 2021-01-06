package io.zmyzheng.processor.impl;

import io.zmyzheng.processor.StreamProcessor;
import io.zmyzheng.processor.model.Tweet;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.elasticsearch.ActionRequestFailureHandler;
import org.apache.flink.streaming.connectors.elasticsearch.ElasticsearchSinkFunction;
import org.apache.flink.streaming.connectors.elasticsearch.RequestIndexer;
import org.apache.flink.streaming.connectors.elasticsearch7.ElasticsearchSink;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.ExceptionUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchParseException;
import org.elasticsearch.action.ActionRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.util.concurrent.EsRejectedExecutionException;
import org.elasticsearch.common.xcontent.XContentType;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-05 22:18
 * @Version 1.0.0
 */
public abstract class KafkaEsProcessor<T> implements StreamProcessor {

    private String kafkaBootstrapServers;
    private String kafkaTopic;
    private String esUrl;
    private String esIndexName;


    private StreamExecutionEnvironment env;
    private DataStream<String> sourceDataStream;
    private DataStream<T> processedDataStream;

    public KafkaEsProcessor(String kafkaBootstrapServers, String kafkaTopic, String esUrl, String esIndexName) {
        this.kafkaBootstrapServers = kafkaBootstrapServers;
        this.kafkaTopic = kafkaTopic;
        this.esUrl = esUrl;
        this.esIndexName = esIndexName;
    }









    @Override
    public void configureStreamExecutionEnvironment() {
        env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRestartStrategy(RestartStrategies.fixedDelayRestart(
                3, // number of restart attempts
                Time.of(10, TimeUnit.SECONDS) // delay
        ));
        env.enableCheckpointing(5000);
        // advanced options:
        // set mode to exactly-once (this is the default)
        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
        // make sure 500 ms of progress happen between checkpoints
        env.getCheckpointConfig().setMinPauseBetweenCheckpoints(500);
        // checkpoints have to complete within one minute, or are discarded
        env.getCheckpointConfig().setCheckpointTimeout(60000);
        // allow only one checkpoint to be in progress at the same time
        env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
        // enable externalized checkpoints which are retained after job cancellation
        env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
        // allow job recovery fallback to checkpoint when there is a more recent savepoint
        // env.getCheckpointConfig().setPreferCheckpointForRecovery(true); // deprecated
    }

    @Override
    public void addSource() {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", kafkaBootstrapServers);
        properties.setProperty("group.id", this.getClass().getName());
        this.sourceDataStream = env.addSource(new FlinkKafkaConsumer<String>(kafkaTopic, new SimpleStringSchema(), properties));


    }

    @Override
    public void defineProcessingLogic() {
        DataStream<String> input = this.sourceDataStream;
        this.processedDataStream = defineProcessingLogic(input);

    }

    public abstract DataStream<T> defineProcessingLogic(DataStream<String> sourceDataStream);



    @Override
    public void addSink() throws Exception {

        this.processedDataStream.addSink(createEsSink());

    }

    private ElasticsearchSink<T> createEsSink() throws MalformedURLException {
        URL url = new URL(esUrl);
        String hostname = url.getHost();
        int port = url.getPort();
        String protocol = url.getProtocol();

        List<HttpHost> httpHosts = new ArrayList<>();
        httpHosts.add(new HttpHost(hostname, port, protocol));


        // use a ElasticsearchSink.Builder to create an ElasticsearchSink
        ElasticsearchSink.Builder<T> esSinkBuilder = new ElasticsearchSink.Builder<>(
                httpHosts,
                new ElasticsearchSinkFunction<T>() {
                    public IndexRequest createIndexRequest(T element) throws JsonProcessingException {
                        ObjectMapper objectMapper = new ObjectMapper();
                        return Requests.indexRequest()
                                .index(esIndexName)
//                                .type("tweets")
//                                .id(tweet.getId())
                                .source(objectMapper.writeValueAsBytes(element), XContentType.JSON);
                    }

                    @Override
                    public void process(T element, RuntimeContext ctx, RequestIndexer indexer) {

                        try {
                            indexer.add(createIndexRequest(element));
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }
        );
        esSinkBuilder.setFailureHandler(new ActionRequestFailureHandler() {
            @Override
            public void onFailure(ActionRequest action, Throwable failure, int restStatusCode, RequestIndexer indexer) throws Throwable {
                if (ExceptionUtils.findThrowable(failure, EsRejectedExecutionException.class).isPresent()) {
                    // full queue; re-add document for indexing
                    indexer.add(action);
                } else if (ExceptionUtils.findThrowable(failure, ElasticsearchParseException.class).isPresent()) {
                    // malformed document; simply drop request without failing sink
                } else {
                    // for all other failures, fail the sink
                    // here the failure is simply rethrown, but users can also choose to throw custom exceptions
                    throw failure;
                }

            }
        });
        return esSinkBuilder.build();
    }

    @Override
    public void process() throws Exception {
        env.execute();
    }
}
