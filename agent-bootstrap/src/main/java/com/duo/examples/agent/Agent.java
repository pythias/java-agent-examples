package com.duo.examples.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.nameContains;
import static net.bytebuddy.matcher.ElementMatchers.nameStartsWith;

/**
 * @author pythias
 * @since 2019-05-27
 */
public class Agent {
    private static final Logger LOGGER = LoggerFactory.getLogger(Agent.class);

    public static void premain(String agentArgs, Instrumentation instrumentation) {
        final PluginFinder pluginFinder = new PluginFinder(new PluginBootstrap().loadPlugins());

        new AgentBuilder.Default()
                .ignore(getIgnores())
                .type(pluginFinder.buildMatch())
                .transform((builder, typeDescription, classLoader, javaModule) -> {
                    PluginEnhance pluginEnhance = pluginFinder.findPlugin(typeDescription.getName());
                    return pluginEnhance == null ? builder : pluginEnhance.define(typeDescription, builder, classLoader);
                })
                .installOn(instrumentation);

        LOGGER.info("Agent started.");
    }


    private static ElementMatcher.Junction<TypeDescription> getIgnores() {
        return nameStartsWith("net.bytebuddy.")
                .or(nameStartsWith("org.slf4j."))
                .or(nameStartsWith("org.apache.logging."))
                .or(nameStartsWith("org.groovy."))
                .or(nameContains("javassist"))
                .or(nameContains(".asm."))
                .or(nameStartsWith("sun.reflect"))
                .or(ElementMatchers.<TypeDescription>isSynthetic());
    }
}
