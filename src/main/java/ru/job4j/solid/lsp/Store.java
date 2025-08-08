package ru.job4j.solid.lsp;

import java.util.HashMap;
import java.util.Map;

public class Store {
    private final Map<String, Integer> map = new HashMap<>();

    public void add(String name) {
        if (map.containsKey(name)) {
            throw new IllegalArgumentException("Элемент с именем '" + name + "' уже есть в карте");
        }
        map.put(name, 1);
    }

    public void findByName(String name) {
        if (!map.containsKey(name)) {
            throw new IllegalArgumentException("Элемент с именем '" + name + "' не найден в карте");
        }
        System.out.println(name + ":" + map.get(name));
    }
}
