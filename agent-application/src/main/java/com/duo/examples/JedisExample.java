package com.duo.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * @author pythias
 * @since 2019-05-27
 */
public class JedisExample {
    private static Logger LOGGER = LoggerFactory.getLogger(JedisExample.class);

    public static void getExample() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.connect();

        LOGGER.info(jedis.set("hello", "world-" + System.currentTimeMillis()));
        LOGGER.info(jedis.get("hello"));
    }
}
