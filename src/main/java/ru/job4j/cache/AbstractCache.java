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
        V rsl = null;
        int attempts = 0;
        while (rsl == null && attempts < 11) {
            SoftReference<V> check = cache.get(key);
            if (check == null) {
                put(key, load(key));
                check = cache.get(key);
            }
            rsl = check != null ? check.get() : null;
            attempts++;
        }
        if (rsl == null) {
            throw new IllegalStateException("Не удалось получить значение из кэша после "
                    + (attempts - 1) + " попыток по ключу " + key);
        }
        return rsl;
    }

    protected abstract V load(K key);
}