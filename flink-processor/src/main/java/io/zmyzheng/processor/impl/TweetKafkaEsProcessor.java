package io.zmyzheng.processor.impl;

import io.zmyzheng.processor.model.Tweet;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.elasticsearch.common.geo.GeoPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.*;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-05 23:30
 * @Version 3.0.0
 */
public class TweetKafkaEsProcessor extends KafkaEsProcessor<Tweet> {
    public TweetKafkaEsProcessor(String kafkaBootstrapServers, String kafkaTopic, String esUrl, String esIndexName) {
        super(kafkaBootstrapServers, kafkaTopic, esUrl, esIndexName);
    }


    @Override
    public DataStream<Tweet> defineProcessingLogic(DataStream<String> sourceDataStream) {
        DataStream<Tweet> tweetDataStream = sourceDataStream.map(new MapFunction<String, Tweet>() {
            @Override
            public Tweet map(String value)  {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    JsonNode node = objectMapper.readTree(value);
                    Tweet tweet = new Tweet();
                    tweet.setId(node.get("id_str").asText());
                    tweet.setTimestamp(Long.parseLong(node.get("timestamp_ms").asText()));

                    ArrayNode arrayNode = (ArrayNode) node.get("coordinates").get("coordinates");
                    tweet.setCoordinate(new GeoPoint(arrayNode.get(1).asDouble(), arrayNode.get(0).asDouble()));

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
        return validTweets;
    }
}
