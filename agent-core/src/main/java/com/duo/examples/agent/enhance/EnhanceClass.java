package com.duo.examples.agent.enhance;

import com.duo.examples.agent.PluginEnhance;
import com.duo.examples.agent.interceptor.MethodInterceptor;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatcher;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author pythias
 * @since 2019-05-29
 */
public abstract class EnhanceClass extends PluginEnhance {
    @Override
    protected DynamicType.Builder<?> enhance(TypeDescription typeDescription, DynamicType.Builder<?> newClassBuilder, ClassLoader classLoader) {
        newClassBuilder = this.enhanceInstance(newClassBuilder, classLoader);
        return newClassBuilder;
    }

    private DynamicType.Builder<?> enhanceInstance(DynamicType.Builder<?> newClassBuilder, ClassLoader classLoader) {
        Map<String, List<ElementMatcher<MethodDescription>>> methodMatchers = getMethodMatchers();
        if (methodMatchers.isEmpty()) {
            return newClassBuilder;
        }

        for (String interceptorClassName : methodMatchers.keySet()) {
            List<ElementMatcher<MethodDescription>> matchers = methodMatchers.get(interceptorClassName);
            if (matchers.isEmpty()) {
                continue;
            }

            for (ElementMatcher<MethodDescription> matcher : matchers) {
                try {
                    MethodInterceptor interceptor = new MethodInterceptor(interceptorClassName, classLoader);
                    newClassBuilder = newClassBuilder.method(matcher).intercept(MethodDelegation.withDefaultConfiguration().to(interceptor));
                } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException ignore) {
                }
            }
        }

        return newClassBuilder;
    }

    protected abstract Map<String, List<ElementMatcher<MethodDescription>>> getMethodMatchers();
}
