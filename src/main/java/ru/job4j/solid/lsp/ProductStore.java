package ru.job4j.solid.lsp;

import java.util.HashMap;
import java.util.Map;

public class ProductStore extends Store {
    private final Map<String, Integer> map = new HashMap<>();

    public void add(String name) {
        map.put(name, 1);
    }

    public void findByName(String name) {
        if (!map.containsKey(name)) {
            System.out.println("Продукт не найден");
        }
        System.out.println(name + ":" + map.get(name));
    }
}
