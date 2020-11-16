package io.zmyzheng.processor;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.streaming.connectors.elasticsearch.ElasticsearchSinkFunction;
import org.apache.flink.streaming.connectors.elasticsearch.RequestIndexer;
import org.apache.flink.streaming.connectors.elasticsearch7.ElasticsearchSink;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentType;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Mingyang Zheng
 * @Date: 2020-02-12 21:56
 */
@Slf4j
public class EsSinkFactory {


    public static ElasticsearchSink<Tweet> getEsSink(String esUrl) throws MalformedURLException {
        URL url = new URL(esUrl);
        String hostname = url.getHost();
        int port = url.getPort();
        String protocol = url.getProtocol();

        List<HttpHost> httpHosts = new ArrayList<>();
        httpHosts.add(new HttpHost(hostname, port, protocol));


        // use a ElasticsearchSink.Builder to create an ElasticsearchSink
        ElasticsearchSink.Builder<Tweet> esSinkBuilder = new ElasticsearchSink.Builder<>(
                httpHosts,
                new ElasticsearchSinkFunction<Tweet>() {
                    public IndexRequest createIndexRequest(Tweet element) throws JsonProcessingException {
                        ObjectMapper objectMapper = new ObjectMapper();
                        return Requests.indexRequest()
                                .index("streaming")
                                .type("tweets")
                                .id(element.getId())
                                .source(objectMapper.writeValueAsBytes(element), XContentType.JSON);
                    }

                    @Override
                    public void process(Tweet element, RuntimeContext ctx, RequestIndexer indexer) {

                        try {
                            indexer.add(createIndexRequest(element));
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }
        );
        return esSinkBuilder.build();
    }

}
