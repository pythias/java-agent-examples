package com.duo.examples;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author pythias
 * @since 2019-05-27
 */
public class HttpClientExample {
    private static Logger LOGGER = LoggerFactory.getLogger(HttpClientExample.class);

    public static void getExample() throws IOException {
        CloseableHttpClient httpClient = HttpClients.custom().build();
        HttpGet httpGet = new HttpGet("http://www.mocky.io/v2/5185415ba171ea3a00704eed");
        out(httpClient.execute(httpGet));
    }

    public static void postExample() throws IOException {
        CloseableHttpClient httpClient = HttpClients.custom().build();
        HttpPost httpPost = new HttpPost("http://www.mocky.io/v2/5185415ba171ea3a00704eed");
        out(httpClient.execute(httpPost));
    }

    private static void out(CloseableHttpResponse response) throws IOException {
        try {
            HttpEntity entity = response.getEntity();
            LOGGER.info(EntityUtils.toString(entity));
        } finally {
            response.close();
        }
    }
}
