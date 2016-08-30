/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csiro.hadoop;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.file.FileReader;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.util.Utf8;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author kho01f
 */
public class PopulateAvroSchema {

  public void serialize() throws IOException {
      // Create a datum to serialize.
//      File avroSchema  = new File("C:\\Users\\kho01f\\Documents\\NetBeansProjects\\Hadoop\\src\\main\\java\\com\\csiro\\hadoop\\ufo.avsc");
//      Schema schema = Schema.parse(avroSchema);
//      GenericRecord datum = new GenericData.Record(schema);
//      datum.put("sighting_date", "2012-01-12");
//      datum.put("city", "London");
//      datum.put("shape", "diamond");
//      datum.put("duration", 0.25);
      
      File avroFile = new File("ufo.avro");
//      DatumWriter<GenericRecord> datumWriter = new SpecificDatumWriter<GenericRecord>();
//      DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
//      dataFileWriter.create(schema, avroFile);
//      dataFileWriter.append(datum);
//      dataFileWriter.close();
      
      DatumReader<GenericRecord> userDatumReader = new GenericDatumReader<GenericRecord>();
      DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(avroFile, userDatumReader);
      
      while(dataFileReader.hasNext()){
          GenericRecord r = dataFileReader.next();
          
          System.out.println(r.get("shape"));
      }
  
   }
}
