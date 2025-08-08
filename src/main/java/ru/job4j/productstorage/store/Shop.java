package ru.job4j.productstorage.store;

import ru.job4j.productstorage.model.Food;

import java.util.List;

public class Shop extends AbstractStore {
    public Shop(List<Food> store) {
        super(store);
    }

    @Override
    public boolean check(Food object) {
        double quality = calculateQualityPercent(object);
        return quality > 0 && quality <= 75;
    }

    @Override
    public void applyDiscount(Food object) {
        if (calculateQualityPercent(object) < 25) {
            object.setPrice(object.getPrice() - (object.getPrice() * object.getDiscount()) / 100);
        }
    }
}
