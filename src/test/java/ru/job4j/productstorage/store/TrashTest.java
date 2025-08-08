package ru.job4j.productstorage.store;

import org.junit.jupiter.api.Test;
import ru.job4j.productstorage.model.Food;
import ru.job4j.productstorage.model.Fruit;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

class TrashTest {
    @Test
    void checkReturnsTrueWhenFoodIsValid() {
        AbstractStore store = new Trash(new ArrayList<>());
        Food food = new Fruit(
                "Apple",
                "Russia",
                LocalDateTime.of(2025, 8, 8, 0, 0, 0),
                LocalDateTime.of(2025, 8, 5, 0, 0, 0),
                100,
                20
        );
        assertThat(store.check(food)).isTrue();
    }

    @Test
    void checkReturnsFalseWhenFoodIsInvalid() {
        AbstractStore store = new Trash(new ArrayList<>());
        Food food = new Fruit(
                "Apple",
                "Russia",
                LocalDateTime.of(2025, 8, 15, 0, 0, 0),
                LocalDateTime.of(2025, 8, 4, 0, 0, 0),
                100,
                20
        );
        assertThat(store.check(food)).isFalse();
    }
}