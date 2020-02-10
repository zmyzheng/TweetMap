package io.github.zmyzheng;

import lombok.extern.slf4j.Slf4j;


import java.io.*;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: Mingyang Zheng
 * @Date: 2020-02-09 15:21
 */
@Slf4j
public class App {

    public static void main(String[] args) throws IOException {
        InputStream in = new FileInputStream(new File("TweetCollector/src/main/resources/application.properties"));

        Properties properties = new Properties();
        properties.load(in);

//        String apiKey = config.get("twitter.apiKey").toString();
//        String apiSecret = config.get("twitter.apiSecret").toString();
//        String token = config.get("twitter.token").toString();
//        String secret = config.get("twitter.secret").toString();
        log.info("Using the following configuration: {}", properties);

        TweetCollector collector = new TweetCollector(
                        properties.get("twitter.apiKey").toString(),
                        properties.get("twitter.apiSecret").toString(),
                        properties.get("twitter.token").toString(),
                        properties.get("twitter.secret").toString(),
                        properties.get("kafka.brokerList").toString(),
                        properties.get("kafka.topic").toString()
        );

        collector.run();

    }
}
