package io.zmyzheng.processor;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Mingyang Zheng
 * @Date: 2020-02-11 16:31
 */
@Slf4j
public class StreamProcessor {

    public void run() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
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
        env.getCheckpointConfig().setPreferCheckpointForRecovery(true);

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "54.212.221.82:9092");
        properties.setProperty("group.id", this.getClass().getName());

        DataStream<String> dataStream = env.addSource(new FlinkKafkaConsumer011<String>("tweets", new SimpleStringSchema(), properties));


        DataStream<Tweet> tweetDataStream = dataStream.map(new MapFunction<String, Tweet>() {
            @Override
            public Tweet map(String value)  {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    JsonNode node = objectMapper.readTree(value);
                    Tweet tweet = new Tweet();
                    tweet.setTimestamp(Long.parseLong(node.get("timestamp_ms").asText()));

                    ArrayNode arrayNode = (ArrayNode) node.get("coordinates").get("coordinates");
                    tweet.setCoordinate(Arrays.asList(arrayNode.get(0).asDouble(), arrayNode.get(1).asDouble()));

                    arrayNode = (ArrayNode) node.get("entities").get("hashtags");
                    List<String> tags = new ArrayList<>();
                    for (JsonNode item : arrayNode) {
                        tags.add(item.get("text").asText());
                    }
                    tweet.setHashTags(tags);
                    return tweet;
                } catch (Exception e) {
                    return null;
                }
            }
        });

        DataStream<Tweet> validTweets = tweetDataStream.filter(new FilterFunction<Tweet>() {
            @Override
            public boolean filter(Tweet tweet) {
                return tweet != null && tweet.getHashTags().size() != 0;
            }
        });

        validTweets.writeAsText("aaa");
        env.execute();

    }

}