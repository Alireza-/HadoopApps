/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csiro.stream.twitter.client;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author kho01f
 */
public class TwitterClientBasicAuth {

    public void readTiwitterStream() throws IOException {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.getCredentialsProvider().setCredentials(
                AuthScope.ANY,
                new UsernamePasswordCredentials("", ""));
        HttpGet httpGet = new HttpGet("https://stream.twitter.com/1/statuses/sample.json");

        //System.out.println("Executing Request" + httpGet);
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();

        System.out.println("................................");
        System.out.println(response.getStatusLine());

        if (entity != null) {
            System.out.println("Response Content Length: " + entity.getContentLength());
        }
        EntityUtils.consume(entity);
        httpClient.getConnectionManager().shutdown();
    }
}
