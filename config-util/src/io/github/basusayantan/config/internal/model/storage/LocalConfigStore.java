package io.github.basusayantan.config.internal.model.storage;

import java.util.TreeMap;

import io.github.basusayantan.config.internal.Constants;

public final class LocalConfigStore extends AbstractConfigStore {

    public LocalConfigStore() {
        super();
        initialize();
    }

    private synchronized void initialize() {
        this.data.put(Constants.LOCAL, new TreeMap<>());
    }
    
    @Override
    public String getValue(String location, String group, String key) {
        return this.getValue(this.data.get(Constants.LOCAL), location, group, key);
    }

    @Override
    public void setValue(String location, String group, String key, String value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setValue'");
    }

    @Override
    public void reload() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reload'");
    }

}
