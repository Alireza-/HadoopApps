/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csiro.stream.twitter.client;

import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StreamingEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 *
 * @author kho01f
 */
public class TwitterStreamClient {

    private static String ConsumerKey = "";
    private static String ConsumerSecret = "";
    private static String AccessToken = "";
    private static String TokenSecret = "";
    private static Authentication auth;
    // private static Client client;
    private static LinkedBlockingQueue<String> msgQueue;

    private static String topic = "twitter";
    private static String partitionKey = "Test_Key";
    private int msgQueueSize = 10000;
    private int msgReadRound = 100;

    public Authentication loadAuthenticationInfo() {
        auth = new OAuth1(ConsumerKey, ConsumerSecret, AccessToken, TokenSecret);
        return auth;
    }

    public Client createClient(String hostAddress, StreamingEndpoint endpoint) {

        msgQueue = new LinkedBlockingQueue<String>(msgQueueSize);

        ClientBuilder clientBuilder = new ClientBuilder()
                .name("Client-01")
                .hosts(hostAddress)
                .authentication(auth)
                .endpoint(endpoint)
                .processor(new StringDelimitedProcessor(msgQueue));

        return clientBuilder.build();
    }

    public void pushMsgToKafkaProducer(Client client, Producer producer) throws InterruptedException {

        client.connect();

        for (int msgRead = 0; msgRead < msgReadRound; msgRead++) {
            if (client.isDone()) {
                System.out.println("Client Connection dead unexpectedly");
                break;
            }
            String msg = msgQueue.poll(5, TimeUnit.SECONDS);
            String txt = extractJSONMsg(msg);
            if (txt == null) {
                System.out.println("Uh oh! Null tweets...");
            } else {
                producer.send(buildKafkaKeyedMsg(txt));
            }
        }
        client.stop();
    }

    public void consumeClientMessage(Client client) throws InterruptedException {

        client.connect();
        for (int msgRead = 0; msgRead < msgReadRound; msgRead++) {
            if (client.isDone()) {
                System.out.println("Client Connection dead unexpectedly");
                break;
            }
            String msg = msgQueue.poll(5, TimeUnit.SECONDS);
            if (msg == null) {
                System.out.println("Did not recieve a message in 5 seconds...");
            } else {
                System.out.println(extractJSONMsg(msg));
            }
        }
        client.stop();
    }

    public String extractJSONMsg(String msg) {

        String txt = null;
        if (msg != null) {
            JSONObject json = (JSONObject) JSONSerializer.toJSON(msg);
            if (json.containsKey("text")) {
                txt = json.getString("text");
            }
        }
        return txt;
    }

    public KeyedMessage<String, String> buildKafkaKeyedMsg(String msg) {
        KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, partitionKey, msg);
        return data;
    }
}
