package io.github.basusayantan.config.internal.model.data;

import java.util.Objects;

public class CompositeKey implements Comparable<CompositeKey> {

    private final String location;
    private final String group;
    private final String key;

    public CompositeKey(String location, String group, String key) {
        this.location = location.toUpperCase();
        this.group = group.toUpperCase();
        this.key = key.toUpperCase();
    }

    public String getLocation() {
        return location;
    }

    public String getGroup() {
        return group;
    }

    public String getKey() {
        return key;
    }

    @Override
    public int compareTo(CompositeKey o) {
        int result = this.location.compareTo(o.getLocation());
        if (result == 0)  {
            result = this.group.compareTo(o.getGroup());
            return  result == 0 ? this.key.compareTo(o.getKey()) : result;
        }
        return result;  
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        CompositeKey other = (CompositeKey) obj;
        return location.equals(other.location)
                && group.equals(other.group)
                && key.equals(other.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.location, this.group, this.key);
    }

    @Override
    public String toString() {
        return String.format("%s.%s.%s", this.location, this.group, this.key);
    }

}
