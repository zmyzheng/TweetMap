package io.zmyzheng.processor;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;

import java.util.Properties;

/**
 * @Author: Mingyang Zheng
 * @Date: 2020-02-11 16:31
 */
public class StreamProcessor {

    public void run() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.enableCheckpointing(5000);

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "54.212.221.82:9092");
        properties.setProperty("group.id", "a");

        DataStream<String> dataStream = env.addSource(new FlinkKafkaConsumer011<String>("tweets", new SimpleStringSchema(), properties));

        dataStream.writeAsText("aaa");
        env.execute();

    }

}