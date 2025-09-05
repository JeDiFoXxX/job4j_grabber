package ru.job4j.algo.tree;

import ru.job4j.algo.tree.collection.*;

import java.util.*;

public class TreeUtils<T> {
    public int countNode(Node<T> root) {
        checkRoot(root);
        SimpleQueue<Node<T>> queue = createQueueWithRoot(root);
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
        checkRoot(root);
        SimpleQueue<Node<T>> queue = createQueueWithRoot(root);
        List<T> listValues = new ArrayList<>();
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

    public boolean add(Node<T> root, T parent, T child) {
        checkRoot(root);
        SimpleQueue<Node<T>> queue = createQueueWithRoot(root);
        boolean rsl = false;
        while (true) {
            try {
                Node<T> cur = queue.poll();
                if (Objects.equals(cur.getValue(), parent)) {
                    cur.getChildren().add(new Node<T>(child));
                    rsl = true;
                    break;
                }
                for (Node<T> node : cur.getChildren()) {
                    queue.push(node);
                }
            } catch (NoSuchElementException e) {
                break;
            }
        }
        return rsl;
    }

    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        checkRoot(root);
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        Optional<Node<T>> optional = Optional.empty();
        stack.push(root);
        while (true) {
            try {
                Node<T> cur = stack.pop();
                if (Objects.equals(cur.getValue(), key)) {
                    optional = Optional.of(cur);
                    break;
                }
                for (Node<T> node : cur.getChildren()) {
                    stack.push(node);
                }
            } catch (NoSuchElementException e) {
                break;
            }
        }
        return optional;
    }

    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        checkRoot(root);
        SimpleQueue<Node<T>> queue = createQueueWithRoot(root);
        Optional<Node<T>> optional = Optional.empty();
        if (Objects.equals(root.getValue(), key)) {
            optional = Optional.of(root);
        }
        while (optional.isEmpty()) {
            try {
                Node<T> cur = queue.poll();
                Iterator<Node<T>> iterator = cur.getChildren().iterator();
                while (iterator.hasNext()) {
                    Node<T> node = iterator.next();
                    if (Objects.equals(node.getValue(), key)) {
                        optional = Optional.of(node);
                        iterator.remove();
                        break;
                    }
                    queue.push(node);
                }
            } catch (NoSuchElementException e) {
                break;
            }
        }
        return optional;
    }

    private void checkRoot(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException("Root can't be null");
        }
    }

    private SimpleQueue<Node<T>> createQueueWithRoot(Node<T> root) {
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        queue.push(root);
        return queue;
    }
}
