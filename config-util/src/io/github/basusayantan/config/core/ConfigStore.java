package io.github.basusayantan.config.core;

public interface ConfigStore {

    String getValue(String location, String group, String key);

    void setValue(String location, String group, String key, String value);

    void reload();

}
