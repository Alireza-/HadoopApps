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
import java.util.Map;
import java.util.Random;

/**
 *
 * @author kho01f
 */
public class SimpleSpout extends BaseRichSpout{

    private SpoutOutputCollector collector;
    
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }

    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
    }

    public void nextTuple() {
        Utils.sleep(100);
        final String[] words = new String[] {"Alireza", "Masi", "Masoumeh", "Morteza"};
        final Random rand = new Random();
        final String word = words[rand.nextInt(words.length)];
        collector.emit(new Values(word));
    }
    
}
