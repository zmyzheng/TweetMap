package io.zmyzheng.processor;

import io.zmyzheng.processor.impl.TweetKafkaEsProcessor;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-05 23:50
 * @Version 3.0.0
 */
public class Application {

    public static void main(String[] args) throws Exception {


        String kafkaBootstrapServers = System.getProperty("kafka.brokerList","application.properties");
        String kafkaTopic = System.getProperty("kafka.topic","application.properties");
        String esUrl = System.getProperty("elasticsearch.url","application.properties");
        String esIndexName = System.getProperty("elasticsearch.indexName","application.properties");


        StreamProcessor processor = new TweetKafkaEsProcessor(kafkaBootstrapServers, kafkaTopic, esUrl, esIndexName);
        StreamProcessingDriver driver = new StreamProcessingDriver(processor);
        driver.run();

    }
}
