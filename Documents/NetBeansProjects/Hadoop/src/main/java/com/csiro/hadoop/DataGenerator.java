/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csiro.hadoop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author kho01f
 */
public class DataGenerator {

    public void generateData(String fileName) throws IOException {

        File file = new File("C:/Users/kho01f/Desktop/PR/" + fileName);
        file.createNewFile();

        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 500000; j++) {
                bw.write("A valid record\n");
            }
            for (int k = 0; k < 5; k++) {
                bw.write("skiptext\n");
            }
        }
        for (int j = 0; j < 500000; j++) {
            bw.write("A valid record\n");
        }
        bw.close();
    }

    public void readFile(String fileName) throws FileNotFoundException, IOException {

        File file = new File("C:/Users/kho01f/Desktop/PR/" + fileName);

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        int i = 0;
        String str = br.readLine();
        while (str != null) {
            if (str.equals("skiptext")) {
                i++;
            }
            str = br.readLine();
        }
        System.out.println(i);

    }

}
