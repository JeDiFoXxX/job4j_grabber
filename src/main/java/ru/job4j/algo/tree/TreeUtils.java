package ru.job4j.algo.tree;

import ru.job4j.algo.tree.collection.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class TreeUtils<T> {
    public int countNode(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        queue.push(root);
        int lastValue = 1;
        while (true) {
            try {
                Node<T> cur = queue.poll();
                for (Node<T> child : cur.getChildren()) {
                    queue.push(child);
                    lastValue++;
                }
            } catch (NoSuchElementException e) {
                break;
            }
        }
        return lastValue;
    }

    public Iterable<T> findAll(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        List<T> listValues = new ArrayList<>();
        queue.push(root);
        while (true) {
            try {
                Node<T> cur = queue.poll();
                listValues.add(cur.getValue());
                for (Node<T> node : cur.getChildren()) {
                    queue.push(node);
                }
            } catch (NoSuchElementException e) {
                break;
            }
        }
        return listValues;
    }
}
