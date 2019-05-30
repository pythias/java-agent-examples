package com.duo.examples.agent.plugin.httpclient;

import com.duo.examples.agent.interceptor.MethodAroundInterceptor;

import java.lang.reflect.Method;

/**
 * @author pythias
 * @since 2019-05-29
 */
public class ExecuteInterceptor implements MethodAroundInterceptor {
    @Override
    public void beforeMethod(Method method, Object[] allArguments, Class<?>[] argumentsTypes) throws Throwable {
        System.out.println("Execute begins");
    }

    @Override
    public Object afterMethod(Method method, Object[] allArguments, Class<?>[] argumentsTypes, Object ret) throws Throwable {
        System.out.println("Execute ends");
        return ret;
    }

    @Override
    public void handleMethodException(Method method, Object[] allArguments, Class<?>[] argumentsTypes, Throwable t) {

    }
}