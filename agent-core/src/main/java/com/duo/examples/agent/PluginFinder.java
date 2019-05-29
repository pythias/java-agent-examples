package com.duo.examples.agent;

import net.bytebuddy.description.NamedElement;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

import java.util.List;

/**
 * @author pythias
 * @since 2019-05-29
 */
class PluginFinder {
    private final List<PluginEnhance> plugins;

    PluginFinder(List<PluginEnhance> plugins) {
        this.plugins = plugins;
    }

    PluginEnhance findPlugin(String name) {
        for (PluginEnhance plugin : plugins) {
            if (plugin.getEnhanceClassName().equals(name)) {
                return plugin;
            }
        }

        return null;
    }

    ElementMatcher<? super TypeDescription> buildMatch() {
        return new ElementMatcher.Junction<NamedElement>() {
            @Override
            public boolean matches(NamedElement namedElement) {
                for (PluginEnhance plugin : plugins) {
                    if (plugin.getEnhanceClassName().equals(namedElement.getActualName())) {
                        return true;
                    }
                }

                return false;
            }

            @Override
            public <U extends NamedElement> Junction<U> and(ElementMatcher<? super U> elementMatcher) {
                return null;
            }

            @Override
            public <U extends NamedElement> Junction<U> or(ElementMatcher<? super U> elementMatcher) {
                return null;
            }
        };
    }
}
