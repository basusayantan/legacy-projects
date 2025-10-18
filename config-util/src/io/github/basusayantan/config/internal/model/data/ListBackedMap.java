package io.github.basusayantan.config.internal.model.data;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;

public final class ListBackedMap implements Map<String, Map<CompositeKey, String>> {

    private final Map<String, Map<CompositeKey, String>> internalMap;
    private final List<String> priorityOrder;

    public ListBackedMap() {
        this.internalMap = new TreeMap<>();
        this.priorityOrder = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.priorityOrder.size();
    }

    @Override
    public boolean isEmpty() {
        return this.internalMap.isEmpty();
    }

    @Override
    public final boolean containsKey(Object key) {
        if(!(key instanceof String))
            return false;
        return this.internalMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        if(!(value instanceof Map<?, ?>))
            return false;
        return this.internalMap.containsValue(value);
    }

    @Override
    public Map<CompositeKey, String> get(Object key) {
        return this.internalMap.get(key);
    }

    @Override
    public Map<CompositeKey, String> put(String key, Map<CompositeKey, String> value) {
        if(!this.internalMap.containsKey(key))
            this.priorityOrder.add(key);
        return this.internalMap.put(key, value);
    }

    @Override
    public Map<CompositeKey, String> remove(Object key) {
        if(!(key instanceof String))
            return Collections.emptyMap();
        this.priorityOrder.remove(key);
        return this.internalMap.remove(key);
    }

    @Override
    @SuppressWarnings("java:S4968") // The upper bound of type variables and wildcards should not be "final"
    public void putAll(Map<? extends String, ? extends Map<CompositeKey, String>> m) {
        Iterator<? extends Map.Entry<? extends String, ? extends Map<CompositeKey, String>>> it = m.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<? extends String, ? extends Map<CompositeKey, String>> entry = it.next();
            this.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        this.priorityOrder.clear();
        this.internalMap.clear();
    }

    @Override
    public Set<String> keySet() { 
        return new AbstractSet<String>() {

            @Override
            public int size() {
                return ListBackedMap.this.priorityOrder.size();
            }

            @Override
            public Iterator<String> iterator() {                
                return new Iterator<String>() {

                    private String lastReturned = null;
                    private final Iterator<String> delegate = ListBackedMap.this.internalMap.keySet().iterator();

                    @Override
                    public boolean hasNext() {
                        return this.delegate.hasNext();
                    }

                    @Override
                    public String next() {
                        if(!this.hasNext())
                            throw new NoSuchElementException("No more elements to iterate");
                        this.lastReturned = this.delegate.next();
                        return this.lastReturned;
                    }

                    @Override
                    public void remove() {
                        if(this.lastReturned == null)
                            throw new IllegalStateException("next() has not been called yet");
                        ListBackedMap.this.internalMap.remove(this.lastReturned);
                        ListBackedMap.this.priorityOrder.remove(this.lastReturned);
                        this.lastReturned = null;
                    }
                    
                };
            }
            
        };
    }

    @Override
    public Collection<Map<CompositeKey, String>> values() {
        
        return new AbstractCollection<Map<CompositeKey,String>>() {

            @Override
            public Iterator<Map<CompositeKey, String>> iterator() {
                return new Iterator<Map<CompositeKey,String>>() {

                    private final Iterator<Map<CompositeKey, String>> delegate = ListBackedMap.this.internalMap.values().iterator();

                    @Override
                    public boolean hasNext() {
                        return this.delegate.hasNext();
                    }

                    @Override
                    public Map<CompositeKey, String> next() {
                        return this.delegate.next();
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException("Unimplemented method 'remove()'");
                    }
                    
                };
            }

            @Override
            public int size() {
                return ListBackedMap.this.internalMap.values().size();
            }
                        
        };
    }

    @Override
    public Set<Map.Entry<String, Map<CompositeKey, String>>> entrySet() {
        return new AbstractSet<Map.Entry<String,Map<CompositeKey,String>>>() {

            @Override
            public int size() {
                return ListBackedMap.this.internalMap.entrySet().size();
            }

            @Override
            public Iterator<Entry<String, Map<CompositeKey, String>>> iterator() {
                return new Iterator<Map.Entry<String,Map<CompositeKey,String>>>() {

                    private Map.Entry<String,Map<CompositeKey,String>> lastReturnedEntry = null;
                    private final Iterator<Map.Entry<String,Map<CompositeKey,String>>> delegate 
                                                        = ListBackedMap.this.internalMap.entrySet().iterator();


                    @Override
                    public boolean hasNext() {
                        return this.delegate.hasNext();
                    }

                    @Override
                    public Entry<String, Map<CompositeKey, String>> next() {
                        this.lastReturnedEntry = this.delegate.next();
                        return this.lastReturnedEntry;
                    }

                    @Override
                    public void remove() {
                        if(this.lastReturnedEntry == null)
                            throw new IllegalStateException("next() has not been called yet");
                        ListBackedMap.this.priorityOrder.remove(this.lastReturnedEntry.getKey());
                        ListBackedMap.this.internalMap.remove(this.lastReturnedEntry.getKey());
                        this.lastReturnedEntry = null;
                    }
                    
                };
            }
            
        };
    }

}
