package io.github.basusayantan.config.factories;

import io.github.basusayantan.config.core.Config;
import io.github.basusayantan.config.internal.CompositeConfig;

public final class ConfigFactory {

    private ConfigFactory() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Config create() {
        return new CompositeConfig();
    }

}
