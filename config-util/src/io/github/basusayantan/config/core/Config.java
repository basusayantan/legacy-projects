package io.github.basusayantan.config.core;

import java.util.List;
import java.util.Map;

public interface Config {
	
	String getValue(String key);

	void setValue(String key, String value);

	List<String> getValueAsList(String key, String separator);
	
	Map<String, String> getValueAsMap(String key);

}
