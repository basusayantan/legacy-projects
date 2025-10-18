package io.github.basusayantan.config.internal.model.storage;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import io.github.basusayantan.config.core.ConfigStore;
import io.github.basusayantan.config.internal.Constants;
import io.github.basusayantan.config.internal.model.data.CompositeKey;
import io.github.basusayantan.config.tools.parsers.EnvParser;
import io.github.basusayantan.config.tools.parsers.Parser;

public final class ExternalConfigStore extends AbstractConfigStore {

    @SuppressWarnings("java:S3077")
    private static volatile ConfigStore shared;

    public static ConfigStore getInstance() {
        if (shared == null) {
            synchronized (ExternalConfigStore.class) {
                if (shared == null)
                    shared = new ExternalConfigStore();
            }
        }
        return shared;
    }

    private ExternalConfigStore() {
        super();
        this.initialize();
    }

    private synchronized void initialize() {

        Parser parser = new EnvParser();

        Map<CompositeKey, String> configMap = parser.parse();
        this.data.put(Constants.ENVIRONMENT, Collections.unmodifiableMap(configMap));
    }

    @Override
    public String getValue(String location, String group, String key) {
        Iterator<String> priority = this.data.keySet().iterator();
        while (priority.hasNext()) {
            String currentSource = priority.next();
            Map<CompositeKey, String> configMap = this.data.get(currentSource);
            if (configMap == null)
                continue;
            return this.getValue(configMap, location, group, key);
        }
        return null;
    }

    @Override
    public void setValue(String location, String group, String key, String value) {
        throw new UnsupportedOperationException("Unimplemented method 'setValue(String, String, String, String)'");
    }

    @Override
    public synchronized void reload() {
        ExternalConfigStore reloadedStore = new ExternalConfigStore();
        shared = reloadedStore;
    }

}
