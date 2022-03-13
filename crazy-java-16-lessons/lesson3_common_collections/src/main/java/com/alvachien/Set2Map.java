package com.alvachien;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

class SimpleEntry<K, V> implements Map.Entry<K, V>, java.io.Serializable {
    private final K key;
    private V value;

    public SimpleEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }
    public SimpleEntry(Map.Entry<? extends K, ? extends V> entry) {
        this.key = entry.getKey();
        this.value = entry.getValue();
    }

    public K getKey() {
        return key;
    }
    public V getValue() {
        return value;
    }
    public V setValue(V value) {
        V oldValue = this.value;
        this.value = value;
        return oldValue;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o.getClass() == SimpleEntry.class) {
            SimpleEntry se = (SimpleEntry)o;
            return se.getKey().equals(getKey());
        }
        return false;
    }
    public int hashCode() {
        return key == null ? 0 : key.hashCode();
    }
    public String toString() {
        return key + " = " + value;
    }
}

public class Set2Map<K, V> extends HashSet<SimpleEntry<K, V>> {
    public void clear() {
        super.clear();
    }

    public boolean containsKey(K key) {
        return super.contains(new SimpleEntry<K, V>(key, null));
    }
    boolean containsValue(Object value) {
        for(SimpleEntry<K, V> se: this ){
            if (se.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
    public V get(Object key) {
        for(SimpleEntry<K, V> se: this) {
            if (se.getKey().equals(key)) {
                return se.getValue();
            }
        }
        return null;
    }
    public V put(K key, V value) {
        add(new SimpleEntry<K, V>(key, value));
        return value;
    }
    public void putAll(Map<? extends K, ? extends V> m) {
        for (K key: m.keySet()) {
            add(new SimpleEntry<K, V>(key, m.get(key)));
        }
    }
    public V removeKey(Object key) {
        for(Iterator<SimpleEntry<K, V>> it = this.iterator(); it.hasNext(); ) {
            SimpleEntry<K, V> en = (SimpleEntry<K, V>)it.next();
            if (en.getKey().equals(key)) {
                V v = en.getValue();
                it.remove();
                return v;
            }
        }
        return null;
    }
    public int size() {
        return super.size();
    }
}
