/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csiro.stream.kafka;

import java.util.Properties;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 *
 * @author kho01f
 */
public class KafkaProducer {

    private static ProducerConfig config;


    public void loadProducerConfig() {

        // Config shopuld be read from config file, later...
        Properties props = new Properties();
        props.put("metadata.broker.list", "localhost:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("partitioner.class", "com.csiro.stream.kafka.SimplePartitioner");
        props.put("request.required.acks", "1");

        config = new ProducerConfig(props);
    }

    public Producer buildProducer() {

        Producer<String, String> producer = new Producer<String, String>(config);
        return producer;
    }

    public void sendMsgToBroker(String topic, String partitionKey, String msg) {

        Producer<String, String> producer = new Producer<String, String>(config);

        KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, partitionKey, msg);

        producer.send(data);

        producer.close();
    }
    

}
