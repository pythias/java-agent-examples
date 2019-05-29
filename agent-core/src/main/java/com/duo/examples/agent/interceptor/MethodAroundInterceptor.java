package com.duo.examples.agent.interceptor;

import java.lang.reflect.Method;

/**
 * @author pythias
 * @since 2019-05-29
 */
public interface MethodAroundInterceptor {
    void beforeMethod(Method method, Object[] allArguments, Class<?>[] argumentsTypes) throws Throwable;
    Object afterMethod(Method method, Object[] allArguments, Class<?>[] argumentsTypes, Object ret) throws Throwable;
    void handleMethodException(Method method, Object[] allArguments, Class<?>[] argumentsTypes, Throwable t);
}
