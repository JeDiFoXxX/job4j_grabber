package ru.job4j.productstorage.store;

import org.junit.jupiter.api.Test;
import ru.job4j.productstorage.model.Food;
import ru.job4j.productstorage.model.Fruit;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

class ShopTest {
    @Test
    void checkReturnsTrueWhenFoodIsValid() {
        AbstractStore store = new Shop(new ArrayList<>());
        Food food = new Fruit(
                "Apple",
                "Russia",
                LocalDateTime.of(2025, 8, 12, 0, 0, 0),
                LocalDateTime.of(2025, 8, 4, 0, 0, 0),
                100,
                20
        );
        assertThat(store.check(food)).isTrue();
    }

    @Test
    void checkReturnsFalseWhenFoodIsInvalid() {
        AbstractStore store = new Shop(new ArrayList<>());
        Food food = new Fruit(
                "Apple",
                "Russia",
                LocalDateTime.of(2025, 8, 7, 0, 0, 0),
                LocalDateTime.of(2025, 8, 4, 0, 0, 0),
                100,
                20
        );
        assertThat(store.check(food)).isFalse();
    }

    @Test
    void whenQualityLessThan20PercentThenApplyDiscount() {
        AbstractStore store = new Shop(new ArrayList<>());
        Food food = new Fruit(
                "Apple",
                "Russia",
                LocalDateTime.of(2025, 8, 10, 0, 0, 0),
                LocalDateTime.of(2025, 8, 1, 0, 0, 0),
                100,
                20
        );
        store.applyDiscount(food);
        assertThat(80.0D).isEqualTo(food.getPrice());
    }
}