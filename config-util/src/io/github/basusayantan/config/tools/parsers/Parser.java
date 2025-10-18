package io.github.basusayantan.config.tools.parsers;

import java.util.Map;

import io.github.basusayantan.config.core.ConfigStore;
import io.github.basusayantan.config.internal.model.data.CompositeKey;

public interface Parser {
    Map<CompositeKey, String> parse();
    Map<CompositeKey, String> parse(ConfigStore source);
}
