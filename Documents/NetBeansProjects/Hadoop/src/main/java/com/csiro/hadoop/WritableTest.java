/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.csiro.hadoop;

import java.util.Set;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.ByteWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Writable;

/**
 *
 * @author kho01f
 */
public class WritableTest {
    
    public static class IntArrayWritable extends ArrayWritable
    {
        public IntArrayWritable()
        {
            super(IntWritable.class);
        }
    }
    
    public static void main(String[] args)
    {
        System.out.println("*** Primitive Writable ***");
        
        BooleanWritable bool1 = new BooleanWritable(true);
        ByteWritable byte1 = new ByteWritable( (byte) 3);
        System.out.printf ("Boolean:%s Byte:%d\n", bool1, byte1.get());
        
        IntWritable int1 = new IntWritable(5);
        IntWritable int2 = new IntWritable(17);
        System.out.printf("I1:%d I2:%d\n", int1.get(), int2.get());
        
        int1.set(int2.get());
        System.out.printf("I1:%d I2:%d\n", int1.get(), int2.get());
        
        Integer int3 = new Integer(23);
        int1.set(int3);    
        System.out.printf("I1:%d I2:%d\n", int1.get(), int2.get());
        
        System.out.println("*** Array Writable ***");
        
        ArrayWritable a = new ArrayWritable (IntWritable.class);
        a.set(new IntWritable[] {new IntWritable(1), new IntWritable(3), new IntWritable(5)});
        
        IntWritable[] values = (IntWritable[]) a.get();
        for (IntWritable i: values)
        {
            System.out.println(i);
        }
        
        IntArrayWritable ia = new IntArrayWritable();
        ia.set(new IntWritable[]{new IntWritable(1), new IntWritable(3), new IntWritable(5)});
        
        IntWritable[] ivalues = (IntWritable[]) ia.get();
        
        ia.set((new LongWritable[]{ new LongWritable(10001)}));
        
        System.out.println("*** Map Writables ***");
        
        MapWritable m = new MapWritable();
        IntWritable key1 = new IntWritable(5);
        NullWritable value1 = NullWritable.get();
        
        m.put(key1, value1);
        System.out.println(m.containsKey(key1));
        System.out.println(m.get(key1));
        m.put(new LongWritable(100000000), key1);
        Set<Writable> keys = m.keySet();
        
        for (Writable k: keys)
            System.out.println(k.getClass());

    }
}
