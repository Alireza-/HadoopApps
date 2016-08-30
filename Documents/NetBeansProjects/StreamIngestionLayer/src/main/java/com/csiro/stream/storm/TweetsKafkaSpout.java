/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csiro.stream.storm;

import backtype.storm.spout.SchemeAsMultiScheme;
import java.util.ArrayList;
import java.util.List;
import kafka.cluster.Broker;
import storm.kafka.BrokerHosts;
import storm.kafka.HostPort;
import storm.kafka.KafkaConfig;
import storm.kafka.SpoutConfig;
import storm.kafka.StaticHosts;
import storm.kafka.StringScheme;
import storm.kafka.ZkHosts;
import storm.kafka.trident.GlobalPartitionInformation;

/**
 *
 * @author kho01f
 */
public class TweetsKafkaSpout {

    public SpoutConfig getKafkaSpoutConfig() {

        GlobalPartitionInformation hostsAndPartitions = new GlobalPartitionInformation();
        hostsAndPartitions.addPartition(0, new HostPort("localhost", 9092));
        BrokerHosts brokerHosts = new StaticHosts(hostsAndPartitions);

        SpoutConfig kafkaSpoutConfig = new SpoutConfig(brokerHosts, "twitter", "", "storm");
        kafkaSpoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
        kafkaSpoutConfig.zkPort = 2181;
        kafkaSpoutConfig.forceStartOffsetTime(-2);

        kafkaSpoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());

        return kafkaSpoutConfig;

    }
}
