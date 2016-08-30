/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csiro.hadoop;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author kho01f
 */
public class UFOLocation {

    public static class MapClass extends Mapper<LongWritable, Text, Text, LongWritable> {

        private final static LongWritable one = new LongWritable(1);
        private static Pattern locationPattern = Pattern.compile("[a-zA-Z]{2}[^a-zA-Z]*$");

        @Override
        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            String line = value.toString();
            String[] fields = line.split("\t");
            String location = fields[2].trim();
            if (location.length() >= 2) {
                Matcher matcher = locationPattern.matcher(location);
                if (matcher.find()) {

                    int start = matcher.start();
                    String state = location.substring(start, start + 2);
                    context.write(new Text(state.toUpperCase()), one);
                }
            }
        }
    }

    public static class LongSumReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

        @Override
        public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
            int total = 0;
            for (LongWritable val : values) {
                total+=val.get();
            }
            context.write(key, new LongWritable(total));
        }
    }
}
