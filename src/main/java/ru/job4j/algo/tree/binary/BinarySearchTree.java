package ru.job4j.algo.tree.binary;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;

    public boolean put(T key) {
        boolean result;
        if (Objects.isNull(root)) {
            root = new Node(key);
            result = true;
        } else {
            result = put(root, key);
        }
        return result;
    }

    private boolean put(Node node, T key) {
        boolean result = false;
        if (node != null && key != null) {
            int compare = key.compareTo(node.key);
            if (compare > 0) {
                if (node.right != null) {
                    put(node.right, key);
                } else {
                    node.right = new Node(key);
                    result = true;
                }
            }
            if (compare < 0) {
                if (node.left != null) {
                    put(node.left, key);
                } else {
                    node.left = new Node(key);
                    result = true;
                }
            }
        }
        return result;
    }

    public boolean contains(T key) {
        return !Objects.equals(find(root, key), null);
    }

    private Node find(Node node, T key) {
        Node rsl = null;
        if (node != null && key != null) {
            if (Objects.equals(node.key, key)) {
                return node;
            }
            int compare = key.compareTo(node.key);
            if (compare > 0 && node.right != null) {
                rsl = find(node.right, key);
            }
            if (compare < 0 && node.left != null) {
                rsl = find(node.left, key);
            }
        }
        return rsl;
    }

    public boolean remove(T key) {
        return false;
    }

    public List<T> inSymmetricalOrder() {
        List<T> result = new ArrayList<>();
        Node node = root;
        return inSymmetricalOrder(node, result);
    }

    private List<T> inSymmetricalOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            inSymmetricalOrder(localRoot.left, list);
            list.add(localRoot.key);
            inSymmetricalOrder(localRoot.right, list);
        }
        return list;
    }

    public List<T> inPreOrder() {
        return inPreOrder(root, new ArrayList<>());
    }

    private List<T> inPreOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            list.add(localRoot.key);
            if (localRoot.left != null) {
                inPreOrder(localRoot.left, list);
            }
            if (localRoot.right != null) {
                inPreOrder(localRoot.right, list);
            }
        }
        return list;
    }

    public List<T> inPostOrder() {
        return inPostOrder(root, new ArrayList<>());
    }

    private List<T> inPostOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            if (localRoot.left != null) {
                inPostOrder(localRoot.left, list);
            }
            if (localRoot.right != null) {
                inPostOrder(localRoot.right, list);
            }
            list.add(localRoot.key);
        }
        return list;
    }

    public T minimum() {
        return Objects.nonNull(root) ? minimum(root).key : null;
    }

    private Node minimum(Node node) {
        Node rsl = node;
        if (node != null && node.left != null) {
            rsl = minimum(node.left);
        }
        return rsl;
    }

    public T maximum() {
        return Objects.nonNull(root) ? maximum(root).key : null;
    }

    private Node maximum(Node node) {
        Node rsl = node;
        if (node != null && node.right != null) {
            rsl = maximum(node.right);
        }
        return rsl;
    }

    @Override
    public String toString() {
        return PrintTree.getTreeDisplay(root);
    }

    private class Node implements VisualNode {
        private final T key;
        private Node left;
        private Node right;

        public Node(T key) {
            this.key = key;
        }

        @Override
        public VisualNode getLeft() {
            return left;
        }

        @Override
        public VisualNode getRight() {
            return right;
        }

        @Override
        public String getText() {
            return key.toString();
        }
    }
}