package com.csiro.stream.main;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import backtype.storm.utils.Utils;
import com.csiro.stream.kafka.KafkaProducer;
import com.csiro.stream.storm.SimpleSpout;
import com.csiro.stream.storm.SplitSentenceBolt;
import com.csiro.stream.storm.TweetsKafkaSpout;
import com.csiro.stream.storm.TweetsSpout;
import com.csiro.stream.storm.WordCountBolt;
import com.csiro.stream.twitter.client.TwitterStreamClient;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesSampleEndpoint;
import kafka.javaapi.producer.Producer;
import org.tomdz.storm.esper.EsperBolt;
import storm.kafka.KafkaSpout;

public class App {

    public static void main(String[] args) throws Exception {

        // TwitterStreamClient twitterClient = new TwitterStreamClient();
        // twitterClient.loadAuthenticationInfo();
        // Client client = twitterClient.createClient(Constants.STREAM_HOST, new StatusesSampleEndpoint());
        // twitterClient.consumeClientMessage(client);
//        KafkaProducer kafkaProducer = new KafkaProducer();
//        kafkaProducer.loadProducerConfig();
//        Producer producer = kafkaProducer.buildProducer();
//        twitterClient.pushMsgToKafkaProducer(client , producer);
//        producer.close();
//        TweetsKafkaSpout tweetsKafkaSpout = new TweetsKafkaSpout();
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("spout", new SimpleSpout(), 3);
        //  builder.setSpout("spout", new TweetsSpout(), 1);
        EsperBolt esperBolt
                = new EsperBolt.Builder()
                .inputs()
                .aliasComponent("some-spout")
                .withFields("a", "b")
                .ofType(Integer.class)
                .toEventType("Test")
                .outputs()
                .outputs().onDefaultStream().emit("min", "max")
                .statements()
                .add("select max(a) as max, min(b) as min from Test.win:length_batch(4)")
                .build();

        builder.setBolt("split", esperBolt, 2).shuffleGrouping("spout");
        //builder.setBolt("count", new WordCountBolt(), 2).fieldsGrouping("split", new Fields("word"));
        Config conf = new Config();
        conf.setDebug(true);
        conf.setNumWorkers(3);

//        StormSubmitter.submitTopology("WordCount", conf, builder.createTopology());
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("test", conf, builder.createTopology());
        Utils.sleep(100000);
        cluster.killTopology("test");
        cluster.shutdown();
    }

}
