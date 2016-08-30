/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csiro.hadoop;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

/**
 *
 * @author kho01f
 */
public class ReduceJoin {

    public static class SalesRecordMapper extends Mapper<Object, Text, Text, Text> {

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

            String record = value.toString();
            String[] parts = record.split("\t");

            context.write(new Text(parts[0]), new Text("sales\t" + parts[1]));
        }
    }

    public static class AccountRecordMapper extends Mapper<Object, Text, Text, Text> {

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

            String record = value.toString();
            String[] parts = record.split("\t");

            context.write(new Text(parts[0]), new Text("accounts\t" + parts[1]));

        }
    }

    public static class ReduceJoinReducer extends Reducer<Text, Text, Text, Text> {

        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

            String name = "";
            double total = 0.0;
            int count = 0;

            for (Text item : values) {

                String[] parts = item.toString().split("\t");
                if (parts[0].equals("sales")) {
                    count++;
                    total += Float.parseFloat(parts[1]);
                } else if (parts[0].equals("accounts")) {
                    name = parts[1];
                }
            }

            String str = String.format("%d\t%f", count, total);
            context.write(new Text(name), new Text(str));
        }
    }
}
