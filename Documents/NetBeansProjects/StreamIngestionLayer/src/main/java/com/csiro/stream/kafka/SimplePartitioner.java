/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.csiro.stream.kafka;

import java.util.Random;
import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

/**
 *
 * @author kho01f
 */

public class SimplePartitioner implements Partitioner <String> {
    
    public SimplePartitioner (VerifiableProperties props){
        
    }
    
    public int partition (String key, int PartitionsNo){
        
        Random rnd = new Random();
        return rnd.nextInt(PartitionsNo);
        
    }
    
    
}
