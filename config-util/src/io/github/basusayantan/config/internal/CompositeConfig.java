package io.github.basusayantan.config.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.basusayantan.config.core.Config;
import io.github.basusayantan.config.core.ConfigStore;
import io.github.basusayantan.config.internal.model.storage.ExternalConfigStore;
import io.github.basusayantan.config.internal.model.storage.LocalConfigStore;

public final class CompositeConfig implements Config {

	public static final Logger LOGGER = LoggerFactory.getLogger(CompositeConfig.class);
	
	private final ConfigStore localStore;
	private final String location;
	private final String group;
	private final Object simpleLock = new Object();


	public CompositeConfig() {
		this(Constants.ALL_STRING, Constants.ALL_STRING);
	}

	public CompositeConfig(String location, String group) {
		this.localStore = new LocalConfigStore();
		this.location = location;
		this.group = group;
		initialize();
	}

	private synchronized void initialize() {
			// Do Something
	}

	@Override
	public String getValue(String key) {
		return ExternalConfigStore.getInstance().getValue(this.location, this.group, key);
	}

	@Override
	public void setValue(String key, String value) {
		if(key != null && value != null) {
			synchronized (simpleLock) {
				this.localStore.setValue(this.location, this.group, key, value);
			}
		}
	}

	@Override
	public List<String> getValueAsList(String key, String separator) {
		String value = this.localStore.getValue(this.location, this.group, key);
		if(value.indexOf(separator) == -1) {
			return new ArrayList<>();
		}
		return Stream.of(value.split(separator)).map(String::trim).collect(Collectors.toList());
	}

	@Override
	public Map<String, String> getValueAsMap(String key) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getValueAsMap'");
	}

}
