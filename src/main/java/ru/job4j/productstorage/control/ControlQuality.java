package ru.job4j.productstorage.control;

import ru.job4j.productstorage.model.Food;
import ru.job4j.productstorage.store.AbstractStore;

import java.util.List;

public class ControlQuality {
    private final List<AbstractStore> stores;
    private final List<Food> foods;

    public ControlQuality(List<AbstractStore> stores, List<Food> foods) {
        this.stores = stores;
        this.foods = foods;
    }

    public void controlQuality() {
        foods.forEach(food ->
                stores.stream()
                        .filter(store -> store.check(food))
                        .forEach(store -> {
                            store.applyDiscount(food);
                            store.add(food);
                        })
        );
    }
}
