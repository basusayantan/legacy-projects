package io.github.basusayantan.config.tools.parsers;

import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import io.github.basusayantan.config.core.ConfigStore;
import io.github.basusayantan.config.internal.Constants;
import io.github.basusayantan.config.internal.model.data.CompositeKey;

public class EnvParser implements Parser {
    
    @Override
    public Map<CompositeKey, String> parse() {
        
        TreeMap<CompositeKey, String> map = new TreeMap<>();
        Properties properties = System.getProperties();
        Enumeration<Object> keys = properties.keys();
        
        while(keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            CompositeKey compositeKey = new CompositeKey(Constants.ALL_STRING, Constants.ALL_STRING, key);
            map.put(compositeKey, properties.getProperty(key));
        }
        
        return map;
    }

    @Override
    public Map<CompositeKey, String> parse(ConfigStore source) {
        throw new UnsupportedOperationException("Unimplemented method 'parse(ConfigStore)'");
    }

}
