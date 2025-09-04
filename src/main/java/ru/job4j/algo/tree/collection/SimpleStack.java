package ru.job4j.algo.tree.collection;

public class SimpleStack<T> {
    private final ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
    }
}