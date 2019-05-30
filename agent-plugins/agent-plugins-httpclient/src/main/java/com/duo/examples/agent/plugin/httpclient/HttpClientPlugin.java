package com.duo.examples.agent.plugin.httpclient;

import com.duo.examples.agent.enhance.EnhanceClass;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pythias
 * @since 2019-05-27
 */
public class HttpClientPlugin extends EnhanceClass {
    public String getEnhanceClassName() {
        return "org.apache.http.impl.client.InternalHttpClient";
    }

    public Map<String, List<ElementMatcher<MethodDescription>>> getMethodMatchers() {
        Map<String, List<ElementMatcher<MethodDescription>>> map = new HashMap<>();

        List<ElementMatcher<MethodDescription>> matchers = new ArrayList<>();
        matchers.add(methodDescription -> methodDescription.getActualName().equals("doExecute"));
        map.put("com.duo.examples.agent.plugin.httpclient.ExecuteInterceptor", matchers);

        return map;
    }
}
