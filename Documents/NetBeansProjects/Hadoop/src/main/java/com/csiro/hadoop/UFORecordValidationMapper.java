/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csiro.hadoop;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author kho01f
 */
public class UFORecordValidationMapper extends Mapper<LongWritable, Text, LongWritable, Text> {

    public enum LineCopunters {

        BAD_LINES,
        TOO_MANY_TABS,
        TOO_FEW_TABS
    };

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        if (validate(line, context)) {
            context.write(key, value);
        }
    }

    private boolean validate(String str, Context context) {
        String[] parts = str.split("\t");
        if (parts.length != 6) {
            if (parts.length < 6) {
                context.getCounter(LineCopunters.TOO_FEW_TABS).increment(1);
            } else {
                context.getCounter(LineCopunters.TOO_MANY_TABS).increment(1);
            }
            context.getCounter(LineCopunters.BAD_LINES).increment(1);
            if (context.getCounter(LineCopunters.BAD_LINES).getValue() % 10 == 0) {
                context.setStatus("Got 10 bad lines.");
                System.err.println("Read another 10 bad lines.");
            }
            return false;
        }
        return true;
    }
}
