package io.zmyzheng.processor;

import org.apache.flink.streaming.connectors.elasticsearch6.ElasticsearchSink;

/**
 * @Author: Mingyang Zheng
 * @Date: 2020-02-11 00:16
 */
public class App {
    public static void main(String[] args) throws Exception {

        ElasticsearchSink<Tweet> elasticsearchSink = EsSinkFactory.getEsSink("https://search-stream-test-orxqsrzwrhlwvifdk6k3sraygi.us-west-2.es.amazonaws.com");


        new StreamProcessor(elasticsearchSink).run();
    }
}
