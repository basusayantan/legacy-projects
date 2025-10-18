package io.github.basusayantan.config.internal.model.storage;

import java.util.Map;

import io.github.basusayantan.config.core.ConfigStore;
import io.github.basusayantan.config.internal.model.data.CompositeKey;
import io.github.basusayantan.config.internal.model.data.ListBackedMap;

abstract class AbstractConfigStore implements ConfigStore {

    protected final Map<String, Map<CompositeKey, String>> data;

    protected AbstractConfigStore() {
        this.data = new ListBackedMap();
    }

    protected String getValue(Map<CompositeKey, String> configMap, String location, String group ,String key) {
        return configMap.get(new CompositeKey(location, group, key));
    }

}
