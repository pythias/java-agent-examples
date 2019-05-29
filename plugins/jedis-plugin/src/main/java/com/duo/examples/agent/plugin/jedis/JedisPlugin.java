package com.duo.examples.agent.plugin.jedis;

import com.duo.examples.agent.enhance.EnhanceClass;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * @author pythias
 * @since 2019-05-27
 */
public class JedisPlugin extends EnhanceClass {
    public String getEnhanceClassName() {
        return "redis.clients.jedis.Jedis";
    }

    public Map<String, List<ElementMatcher<MethodDescription>>> getMethodMatchers() {
        Map<String, List<ElementMatcher<MethodDescription>>> map = new HashMap<>();

        List<ElementMatcher<MethodDescription>> matchers = new ArrayList<>();
        matchers.add(named("get").or(named("set")));
        map.put("com.duo.examples.agent.plugin.jedis.JedisMethodInterceptor", matchers);

        return map;
    }
}
