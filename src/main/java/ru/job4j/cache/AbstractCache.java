package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public final V get(K key) {
        V rsl;
        SoftReference<V> check = cache.get(key);
        if (check != null) {
            rsl = check.get();
        } else {
            V text = load(key);
            put(key, text);
            rsl = text;
        }
        return rsl;
    }

    protected abstract V load(K key);
}