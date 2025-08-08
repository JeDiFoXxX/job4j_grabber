package ru.job4j.productstorage.store;

import org.junit.jupiter.api.Test;
import ru.job4j.productstorage.model.Food;
import ru.job4j.productstorage.model.Fruit;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

class AbstractStoreTest {

    @Test
    void whenQualityPositiveReturns37Dot5() {
        AbstractStore store = new Shop(new ArrayList<>());
        Food food = new Fruit(
                "Apple",
                "Russia",
                LocalDateTime.of(2025, 8, 12, 0, 0, 0),
                LocalDateTime.of(2025, 8, 4, 0, 0, 0),
                100,
                20
        );
        assertThat(37.5D).isEqualTo(store.calculateQualityPercent(food));
    }

    @Test
    void whenQualityNegativeReturnsMinus12Dot5() {
        AbstractStore store = new Shop(new ArrayList<>());
        Food food = new Fruit(
                "Apple",
                "Russia",
                LocalDateTime.of(2025, 8, 7, 0, 0, 0),
                LocalDateTime.of(2025, 7, 30, 0, 0, 0),
                100,
                20
        );
        assertThat(-12.5D).isEqualTo(store.calculateQualityPercent(food));
    }
}