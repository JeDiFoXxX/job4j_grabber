package ru.job4j.productstorage.store;

import ru.job4j.productstorage.model.Food;

import java.util.List;

public class Warehouse extends AbstractStore {
    public Warehouse(List<Food> store) {
        super(store);
    }

    @Override
    public boolean check(Food object) {
        return calculateQualityPercent(object) > 75;
    }
}
