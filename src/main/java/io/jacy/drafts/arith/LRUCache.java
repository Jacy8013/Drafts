package io.jacy.drafts.arith;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Jacy
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private int capacity;

    public LRUCache(int capacity) {
        super(16, 1f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return this.size() > capacity;
    }
}
