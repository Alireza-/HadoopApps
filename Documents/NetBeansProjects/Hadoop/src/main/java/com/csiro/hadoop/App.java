package com.csiro.hadoop;

import com.csiro.hadoop.GraphPath.*;
import com.csiro.hadoop.ReduceJoin.*;
import com.csiro.hadoop.UFOLocation1.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.chain.ChainMapper;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 *
 *
 */
public class App {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, URISyntaxException {

        
        Configuration conf = new Configuration();
        Job job = new Job(conf, "WordCount");

        job.setJarByClass(WordCount.class);
        job.setMapperClass(WordCount.WordCountMapper.class);
        job.setReducerClass(WordCount.WordCountReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
        
        
//        Configuration conf = new Configuration();
//        Job job = new Job(conf, "SkipData");
//
//        job.setJarByClass(SkipData.class);
//        job.setMapperClass(SkipData.MapClass.class);
//        job.setCombinerClass(LongSumReducer.class);
//        job.setReducerClass(LongSumReducer.class);
//
//        job.setOutputKeyClass(Text.class);
//        job.setOutputValueClass(LongWritable.class);
//
//        FileInputFormat.addInputPath(job, new Path(args[0]));
//        FileOutputFormat.setOutputPath(job, new Path(args[1]));
//
//        System.exit(job.waitForCompletion(true) ? 0 : 1);

//        Configuration conf = new Configuration();
//        Job job = new Job(conf, "Graph Path");
//        
////        job.addCacheFile(new URI("states.txt"));
//
//        job.setJarByClass(GraphPath.class);
//        job.setMapperClass(GraphPathMapper.class);
//        job.setReducerClass(GraphPathReducer.class);
//
//        job.setOutputKeyClass(Text.class);
//        job.setOutputValueClass(Text.class);
//        
////        Configuration map1Conf = new Configuration(false);
////        ChainMapper.addMapper(job, UFORecordValidationMapper.class, LongWritable.class, Text.class, LongWritable.class, Text.class, map1Conf);
////
////        Configuration map2Conf = new Configuration(false);
////        ChainMapper.addMapper(job, MapClass.class, LongWritable.class, Text.class, Text.class, LongWritable.class, map2Conf);
//
////        job.setMapperClass(ChainMapper.class);
////        job.setCombinerClass(LongSumReducer.class);
////        job.setReducerClass(LongSumReducer.class);
//
//        FileInputFormat.addInputPath(job, new Path(args[0]));
//        FileOutputFormat.setOutputPath(job, new Path(args[1]));
//        System.exit(job.waitForCompletion(true) ? 0 : 1);
//
//    }
//    
//    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException{
//        
//        Configuration conf = new Configuration();
//        Job job = new Job(conf, "Reduce-side join");
//        job.setJarByClass(ReduceJoin.class);
//        job.setReducerClass(ReduceJoinReducer.class);
//        job.setOutputKeyClass(Text.class);
//        job.setOutputValueClass(Text.class);
//        
//        MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, SalesRecordMapper.class);
//        
//        MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class, AccountRecordMapper.class);
//        
//        Path outputPath = new Path(args[2]);
//        
//        FileOutputFormat.setOutputPath(job, outputPath);
//        outputPath.getFileSystem(conf).delete(outputPath, true);
//        
//        System.exit(job.waitForCompletion(true) ? 0 : 1);
//
    }

}
