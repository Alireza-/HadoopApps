/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csiro.stream.storm;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;
import com.csiro.stream.twitter.client.TwitterStreamClient;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesSampleEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kho01f
 */
public class TweetsSpout extends BaseRichSpout {

    private SpoutOutputCollector _collector;
    private Client client;
    private LinkedBlockingQueue<String> msgQueue;
    private TwitterStreamClient twitterClient ;
    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        _collector = collector;
       
        twitterClient = new TwitterStreamClient();
        msgQueue = new LinkedBlockingQueue<String>(1000);

        ClientBuilder clientBuilder = new ClientBuilder()
                .name("Client-01")
                .hosts(Constants.STREAM_HOST)
                .authentication(twitterClient.loadAuthenticationInfo())
                .endpoint(new StatusesSampleEndpoint())
                .processor(new StringDelimitedProcessor(msgQueue));
        client = clientBuilder.build();
        client.connect();

    }

    @Override
    public void nextTuple() {
        try {
            String msg = msgQueue.poll(5, TimeUnit.SECONDS);
            String txt = twitterClient.extractJSONMsg(msg);
            if (txt != null) {
                _collector.emit(new Values(txt));
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TweetsSpout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }

    @Override
    public void close() {
        client.stop();
    }
}
