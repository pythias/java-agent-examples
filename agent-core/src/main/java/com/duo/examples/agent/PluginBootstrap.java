package com.duo.examples.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pythias
 * @since 2019-05-29
 */
class PluginBootstrap {
    private static final Logger LOGGER = LoggerFactory.getLogger(PluginBootstrap.class);

    List<PluginEnhance> loadPlugins() {
        List<String> pluginNames = Arrays.asList("com.duo.examples.agent.plugin.httpclient.HttpClientPlugin"
                , "com.duo.examples.agent.plugin.jedis.JedisPlugin"
                , "com.duo.examples.agent.plugin.okhttp.OkhttpPlugin");

        List<PluginEnhance> pluginEnhances = new ArrayList<>();
        pluginNames.forEach(pluginName -> {
            try {
                Class<?> clazz = Class.forName(pluginName, true, PluginLoader.getInstance());
                pluginEnhances.add((PluginEnhance) clazz.getDeclaredConstructor().newInstance());
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });

        return pluginEnhances;
    }
}
