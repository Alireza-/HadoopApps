/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csiro.hadoop;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author kho01f
 */
public class WordCount {

    public static class WordCountMapper extends Mapper<Object, Text, Text, IntWritable> {

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        @Override
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

            String[] words = value.toString().split(" ");

            for (String str : words) {
                word.set(str);
                context.write(word, one);
            }
        }
    }

    public static class WordCountTimeout extends Mapper<Object, Text, Text, IntWritable> {

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        @Override
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

            String lockfile = "/user/hadoop/hdfs1.lock";
            Configuration conf = new Configuration();
            FileSystem hdfs = FileSystem.get(conf);
            Path path = new Path(lockfile);

            if (!hdfs.exists(path)) {
                byte[] bytes = "A lockfile".getBytes();
                FSDataOutputStream out = hdfs.create(path);
                out.write(bytes, 0, bytes.length);
                out.close();
                TimeUnit.SECONDS.sleep(100);
            }
            
            String[] words = value.toString().split(" ");
            
            for (String str : words){
                word.set(str);
                context.write(word,one);
            }
        }
    }

    public static class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

        @Override
        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int total = 0;
            for (IntWritable val : values) {
                total++;
            }
            context.write(key, new IntWritable(total));
        }
    }

    public static class SummaryMapper extends Mapper<Object, Text, Text, IntWritable> {

        private final static IntWritable one = new IntWritable();
        private final Text word = new Text();
        private final String[] str = new String[]{"sighted", "recorded", "location", "shape", "duration", "description"};
        private int time;

        @Override
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String[] records = value.toString().split("\t");
            Pattern pattern = Pattern.compile("\\d+");
            if ((records.length == 6) && (!records[3].isEmpty()) && (!records[4].isEmpty())) {
                Matcher matcher = pattern.matcher(records[4]);
                boolean flag = records[4].contains("min");
                for (int i = 0; i <= matcher.groupCount(); i++) {
                    if (matcher.find(i)) {
                        if (flag) {
                            time = Integer.parseInt(matcher.group()) * 60;
                        } else {
                            time = Integer.parseInt(matcher.group());
                        }
                    }
                }
            }
            one.set(time);
            word.set(records[3]);
            context.write(word, one);
        }
    }

    public static class SummaryReducer extends Reducer<Text, IntWritable, Text, Text> {

        @Override
        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

            int min = 0;
            int max = 0;
            int mean = 0;
            int total = 0;
            int sum = 0;

            for (IntWritable time : values) {
                sum += time.get();
                total++;
                if (time.get() > max) {
                    max = time.get();
                }
                if (time.get() < min) {
                    min = time.get();
                }
            }
            mean = sum / total;
            context.write(key, new Text(" " + min + " " + mean + " " + max));
        }
    }

}
