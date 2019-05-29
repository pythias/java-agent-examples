package com.duo.examples;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author pythias
 * @since 2019-05-29
 */
public class OkHttpExample {
    private static Logger LOGGER = LoggerFactory.getLogger(OkHttpExample.class);

    public static void getExample() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://www.mocky.io/v2/5185415ba171ea3a00704eed")
                .build();
        Response response = client.newCall(request).execute();
        LOGGER.info(response.body().string());
    }
}
