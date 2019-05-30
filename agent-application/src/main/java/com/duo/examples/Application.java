package com.duo.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author pythias
 * @since 2019-05-24
 */
public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws IOException {
        LOGGER.info("Application started.");

        HttpClientExample.getExample();
        HttpClientExample.postExample();

        JedisExample.getExample();

        OkHttpExample.getExample();
    }
}
