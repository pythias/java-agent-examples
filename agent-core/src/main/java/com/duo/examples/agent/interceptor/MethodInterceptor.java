package com.duo.examples.agent.interceptor;

import com.duo.examples.agent.PluginLoader;
import net.bytebuddy.implementation.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * @author pythias
 * @since 2019-05-29
 */
public class MethodInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodInterceptor.class);

    private MethodAroundInterceptor methodAroundInterceptor = null;

    public MethodInterceptor(String interceptorClassName, ClassLoader classLoader) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        methodAroundInterceptor = (MethodAroundInterceptor) Class.forName(interceptorClassName, true, new PluginLoader("method-loader", classLoader)).getConstructor().newInstance();
    }

    @RuntimeType
    public Object intercept(@This Object target, @AllArguments Object[] allArguments, @SuperCall Callable<?> zuper, @Origin Method method) throws Throwable {
        methodAroundInterceptor.beforeMethod(method, allArguments, method.getParameterTypes());
        Object result = null;
        try {
            result = zuper.call();
        } catch (Throwable t) {
            try {
                methodAroundInterceptor.handleMethodException(method, allArguments, method.getParameterTypes(), t);
            } catch (Throwable t2) {
                LOGGER.error("Exception occurs when handling method exception at {}@{}", method.getName(), target.getClass());
            }
            throw t;
        } finally {
            try {
                result = methodAroundInterceptor.afterMethod(method, allArguments, method.getParameterTypes(), result);
            } catch (Throwable t2) {
                LOGGER.error("Exception occurs when handling after method at {}@{}", method.getName(), target.getClass());
            }
        }

        return result;
    }
}
