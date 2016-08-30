/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csiro.hadoop;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author kho01f
 */
public class UFOLocation1 {

    public static class MapClass extends Mapper<LongWritable, Text, Text, LongWritable> {

        private final static LongWritable one = new LongWritable(1);
        private static Pattern locationPattern = Pattern.compile("[a-zA-Z]{2}[^a-zA-Z]*$");
        private Map<String, String> stateName;

        @Override
        public void setup(Context context) throws IOException {
                
               // Configuration conf = context.getConfiguration();
                // retreive an array of files, but we already know there is only one file at Cache!
                URI[] cacheFile = context.getCacheFiles();
                setupStateMap(cacheFile[0].toString());
       
        }

        public void setupStateMap(String fileName) throws FileNotFoundException, IOException {

            Map<String, String> states = new HashMap<String, String>();

            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split("\t");
                states.put(parts[0], parts[1]);
                line = reader.readLine();
            }
            stateName = states;
        }

        public String lookupState(String state) {
            
            String fullName = stateName.get(state);
            if (fullName == null) {
                fullName = "Other";
            }
            return fullName;
        }

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
                    context.write(new Text(lookupState(state.toUpperCase())), one);
                }
            }
        }
    }

    public static class LongSumReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

        @Override
        public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
            int total = 0;
            for (LongWritable val : values) {
                total += val.get();
            }
            context.write(key, new LongWritable(total));
        }
    }
}
