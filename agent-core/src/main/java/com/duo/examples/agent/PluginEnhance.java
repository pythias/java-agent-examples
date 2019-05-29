package com.duo.examples.agent;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;

/**
 * @author pythias
 * @since 2019-05-29
 */
public abstract class PluginEnhance {
    DynamicType.Builder<?> define(TypeDescription typeDescription, DynamicType.Builder<?> builder, ClassLoader classLoader) {
        return this.enhance(typeDescription, builder, classLoader);
    }

    protected abstract String getEnhanceClassName();
    protected abstract DynamicType.Builder<?> enhance(TypeDescription typeDescription, DynamicType.Builder<?> newClassBuilder, ClassLoader classLoader);
}
