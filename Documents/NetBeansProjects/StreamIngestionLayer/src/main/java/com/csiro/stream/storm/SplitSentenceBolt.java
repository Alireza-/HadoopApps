/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csiro.stream.storm;

import backtype.storm.task.ShellBolt;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import java.util.Map;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 *
 * @author kho01f
 */
public class SplitSentenceBolt extends BaseBasicBolt {

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

    @Override
    public void execute(Tuple tuple, BasicOutputCollector collector){
        String msg = tuple.getString(0);
        final String[] words = msg.split("\\s+");
        for (String word: words){
            if (word != null)
                collector.emit(new Values(word));
               }
    }
    
//    @Override
//    public void execute(Tuple tuple, BasicOutputCollector collector) {
//        String txt = null;
//        String msg = tuple.getString(0);
//        if (msg != null) {
//            JSONObject json = (JSONObject) JSONSerializer.toJSON(msg);
//            if (json.containsKey("text")) {
//                txt = json.getString("text");
//                final String[] words = txt.split("\\s+");
//                for (String word : words) {
//                    if (word != null) {
//                        collector.emit(new Values(word));
//                    }
//                }
//            }
//        }
//    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }
}
