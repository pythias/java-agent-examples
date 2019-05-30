package com.duo.examples.agent.plugin.okhttp;

import com.duo.examples.agent.interceptor.MethodAroundInterceptor;

import java.lang.reflect.Method;

/**
 * @author pythias
 * @since 2019-05-29
 */
public class RealCallInterceptor implements MethodAroundInterceptor {
    @Override
    public void beforeMethod(Method method, Object[] allArguments, Class<?>[] argumentsTypes) throws Throwable {
        System.out.println("RealCall begins");
    }

    @Override
    public Object afterMethod(Method method, Object[] allArguments, Class<?>[] argumentsTypes, Object result) throws Throwable {
        return result;
    }

    @Override
    public void handleMethodException(Method method, Object[] allArguments, Class<?>[] argumentsTypes, Throwable t) {

    }
}
