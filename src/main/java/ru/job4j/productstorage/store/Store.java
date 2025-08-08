package ru.job4j.productstorage.store;

import java.util.List;

public interface Store<T> {
    void add(T object);

    void delete(T object);

    List<T> findAll();
}
