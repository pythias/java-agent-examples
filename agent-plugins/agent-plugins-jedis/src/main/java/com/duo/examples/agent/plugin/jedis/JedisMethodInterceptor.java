package com.duo.examples.agent.plugin.jedis;

import com.duo.examples.agent.interceptor.MethodAroundInterceptor;

import java.lang.reflect.Method;

/**
 * @author pythias
 * @since 2019-05-29
 */
public class JedisMethodInterceptor implements MethodAroundInterceptor {
    @Override
    public void beforeMethod(Method method, Object[] allArguments, Class<?>[] argumentsTypes) throws Throwable {
        System.out.println("Jedis begins");
    }

    @Override
    public Object afterMethod(Method method, Object[] allArguments, Class<?>[] argumentsTypes, Object result) throws Throwable {
        System.out.println("Jedis ends");
        return result;
    }

    @Override
    public void handleMethodException(Method method, Object[] allArguments, Class<?>[] argumentsTypes, Throwable t) {

    }
}
