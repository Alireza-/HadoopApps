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

/**
 *
 * @author kho01f
 */
public class GraphPath {

    public static class Node {

        private String id;
        private String neighbours;
        private int distance;
        private String state;

        Node(Text t) {
            String[] parts = t.toString().split("\t");
            this.id = parts[0];
            this.neighbours = parts[1];
            if (parts.length < 3 || parts[2].equals("")) {
                this.distance = -1;
            } else {
                try {
                    this.distance = Integer.parseInt(parts[2]);
                } catch (Exception e) {
                    System.out.println("Value:" + parts[2]);
                    this.distance = 999;
                }
            }

            if (parts.length < 4 || parts[3].equals("")) {
                this.state = "P";
            } else {
                this.state = parts[3];
            }
        }

        Node(Text key, Text value) {
            this(new Text(key.toString() + "\t" + value.toString()));
        }

        String getId() {
            return this.id;
        }

        public String getNeighbours() {
            return this.neighbours;
        }

        public int getDistance() {
            return this.distance;
        }

        public String getState() {
            return this.state;
        }
    }

    public static class GraphPathMapper
            extends Mapper<Object, Text, Text, Text> {

        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            Node n = new Node(value);
            if (n.getState().equals("C")) {
                context.write(new Text(n.getId()), new Text(n.getNeighbours() + "\t" + n.getDistance() + "\t" + "D"));

                for (String neighbour : n.getNeighbours().split(",")) {
                    context.write(new Text(neighbour), new Text("\t" + (n.getDistance() + 1) + "\tC"));
                }
            } else {
                context.write(new Text(n.getId()), new Text(n.getNeighbours() + "\t" + n.getDistance() + "\t" + n.getState()));
            }

        }
    }

    public static class GraphPathReducer
            extends Reducer<Text, Text, Text, Text> {

        public void reduce(Text key, Iterable<Text> values,
                Context context
        ) throws IOException, InterruptedException {
            String neighbours = null;
            int distance = -1;
            String state = "P";

            for (Text t : values) {
                Node n = new Node(key, t);

                if (n.getState().equals("D")) {
                    neighbours = n.getNeighbours();
                    distance = n.getDistance();
                    state = n.getState();
                    break;
                }

                if (n.getNeighbours() != null && !n.getNeighbours().equals("")) {
                    neighbours = n.getNeighbours();
                }

                if (n.getDistance() > distance) {
                    distance = n.getDistance();
                }

                if (n.getState().equals("D") || (n.getState().equals("C") && state.equals("P"))) {
                    state = n.getState();
                }
            }
            context.write(key, new Text(neighbours + "\t" + distance + "\t" + state));
        }
    }
}