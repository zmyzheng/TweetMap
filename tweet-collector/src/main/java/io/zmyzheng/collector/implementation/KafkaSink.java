package io.zmyzheng.collector.implementation;


import io.zmyzheng.collector.Sinkable;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-02 19:48
 * @Version 3.0.0
 */
@Slf4j
public class KafkaSink implements Sinkable<String> {

    private String topic;
    private String brokerList;

    private KafkaProducer<String, String> producer;

    public KafkaSink(String brokerList, String topic) {
        this.topic = topic;
        this.brokerList = brokerList;
    }

    @Override
    public void connect() {
        // create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.brokerList);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // create safe Producer
        properties.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
        properties.setProperty(ProducerConfig.RETRIES_CONFIG, Integer.toString(Integer.MAX_VALUE));
        properties.setProperty(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "5"); // kafka 2.0 >= 1.1 so we can keep this as 5. Use 1 otherwise.

        // high throughput producer (at the expense of a bit of latency and CPU usage)
        properties.setProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
        properties.setProperty(ProducerConfig.LINGER_MS_CONFIG, "20");
        properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, Integer.toString(32*1024)); // 32 KB batch size

        // create the producer
        this.producer = new KafkaProducer<String, String>(properties);

    }

    @Override
    public void send(String data) {
        if (data != null){
//                log.debug(msg);
            producer.send(new ProducerRecord<>(topic, null, data), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e != null) {
                        log.error("Something bad happened", e);
                    }
                }
            });
        }

    }

    @Override
    public void close() {
        log.info("shutting down Kafka Producer...");
        this.producer.close();
        log.info("Close Kafka Producer: done!");
    }
}
