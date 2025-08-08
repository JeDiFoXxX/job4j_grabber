package ru.job4j.productstorage.store;

import ru.job4j.productstorage.model.Food;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public abstract class AbstractStore implements Store<Food> {
    private final List<Food> store;

    public AbstractStore(List<Food> store) {
        this.store = store;
    }

    public abstract boolean check(Food object);

    public void applyDiscount(Food object) {  }

    protected double calculateQualityPercent(Food object) {
        long total = Duration.between(object.getCreateDate(), object.getExpiryDate()).toDays();
        long quality = Duration.between(LocalDateTime.now(), object.getExpiryDate()).toDays();
        return ((double) quality / total) * 100;
    }

    @Override
    public void add(Food object) {
        store.add(object);
    }

    @Override
    public void delete(Food object) {
        store.remove(object);
    }

    @Override
    public List<Food> findAll() {
        return store;
    }
}
