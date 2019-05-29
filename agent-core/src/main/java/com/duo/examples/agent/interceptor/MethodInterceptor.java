package com.duo.examples.agent.interceptor;

import com.duo.examples.agent.PluginLoader;
import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * @author pythias
 * @since 2019-05-29
 */
public class MethodInterceptor {
    private MethodAroundInterceptor methodAroundInterceptor = null;

    public MethodInterceptor(String interceptorClassName, ClassLoader classLoader) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        methodAroundInterceptor = (MethodAroundInterceptor) Class.forName(interceptorClassName, true, new PluginLoader("method-loader", classLoader)).getConstructor().newInstance();
    }

    @RuntimeType
    public Object intercept(@This Object obj, @AllArguments Object[] allArguments, @SuperCall Callable<?> zuper, @Origin Method method) throws Throwable {
        methodAroundInterceptor.beforeMethod(method, allArguments, method.getParameterTypes());
        Object result = null;
        try {
            result = zuper.call();
        } catch (Throwable t) {
            methodAroundInterceptor.handleMethodException(method, allArguments, method.getParameterTypes(), t);
            throw t;
        } finally {
            return methodAroundInterceptor.afterMethod(method, allArguments, method.getParameterTypes(), result);
        }
    }
}
