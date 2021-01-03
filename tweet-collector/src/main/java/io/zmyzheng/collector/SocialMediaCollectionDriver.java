package io.zmyzheng.collector;

import io.zmyzheng.collector.implementation.KafkaSink;
import io.zmyzheng.collector.implementation.TweetCollector;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-02 20:34
 * @Version 3.0.0
 */
@Slf4j
public class SocialMediaCollectionDriver {

    public static void main(String[] args) {

        String apiKey = System.getProperty("twitter.apiKey:","application.properties");
        String apiSecret = System.getProperty("twitter.apiSecret","application.properties");
        String token = System.getProperty("twitter.token","application.properties");
        String secret = System.getProperty("twitter.secret","application.properties");

        String brokerList = System.getProperty("kafka.brokerList","application.properties");
        String topic = System.getProperty("kafka.topic","application.properties");



        SocialMediaCollector<String> collector = new TweetCollector(apiKey, apiSecret, token, secret);

        Sinkable<String> sink = new KafkaSink(brokerList, topic);

        collector.start();
        sink.connect();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

            log.info("shutting down application...");
            collector.stop();
            sink.close();
            log.info("shut down application: done!");
        }));

        while (true) {
            String message = collector.collect();
            sink.send(message);
        }
    }
}
