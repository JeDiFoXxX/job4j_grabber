package ru.job4j.productstorage.control;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.productstorage.model.Food;
import ru.job4j.productstorage.model.Fruit;
import ru.job4j.productstorage.model.Vegetable;
import ru.job4j.productstorage.store.AbstractStore;
import ru.job4j.productstorage.store.Shop;
import ru.job4j.productstorage.store.Trash;
import ru.job4j.productstorage.store.Warehouse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {
    private List<Food> foods;
    private List<AbstractStore> stores;

    @BeforeEach
    void setUp() {
        foods = List.of(
                new Fruit(
                        "Apple",
                        "Russia",
                        LocalDateTime.of(2025, 8, 12, 0, 0, 0),
                        LocalDateTime.of(2025, 8, 4, 0, 0, 0),
                        100,
                        20
                ),
                new Fruit(
                        "Orange",
                        "Spain",
                        LocalDateTime.of(2025, 10, 1, 0, 0, 0),
                        LocalDateTime.of(2025, 8, 1, 0, 0, 0),
                        150,
                        15
                ),
                new Vegetable(
                        "Tomato",
                        "Italy",
                        LocalDateTime.now(),
                        LocalDateTime.of(2025, 7, 1, 0, 0, 0),
                        80,
                        10
                )
        );

        stores = List.of(
                new Shop(new ArrayList<>()),
                new Warehouse(new ArrayList<>()),
                new Trash(new ArrayList<>())
        );
    }

    @Disabled
    @Test
    void whenAppleThenGoesToShopStore() {
        List<Food> expected = List.of(new Fruit(
                        "Apple",
                        "Russia",
                        LocalDateTime.of(2025, 8, 12, 0, 0, 0),
                        LocalDateTime.of(2025, 8, 4, 0, 0, 0),
                        100,
                        20
                )
        );
        ControlQuality control = new ControlQuality(stores, foods);
        control.controlQuality();
        assertThat(stores.get(0).findAll()).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Disabled
    @Test
    void whenOrangeThenGoesToWarehouseStore() {
        List<Food> expected = List.of(new Fruit(
                        "Orange",
                        "Spain",
                        LocalDateTime.of(2025, 10, 1, 0, 0, 0),
                        LocalDateTime.of(2025, 8, 1, 0, 0, 0),
                        150,
                        15
                )
        );
        ControlQuality control = new ControlQuality(stores, foods);
        control.controlQuality();
        assertThat(stores.get(1).findAll()).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Disabled
    @Test
    void whenTomatoThenGoesToTrashStore() {
        List<Food> expected = List.of(new Vegetable(
                        "Tomato",
                        "Italy",
                        LocalDateTime.now(),
                        LocalDateTime.of(2025, 7, 1, 0, 0, 0),
                        80,
                        10
                )
        );
        ControlQuality control = new ControlQuality(stores, foods);
        control.controlQuality();
        assertThat(stores.get(2).findAll()).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Disabled
    @Test
    void whenResortThenCorrectDistribution() {
        Fruit apple = new Fruit(
                "Apple",
                "Russia",
                LocalDateTime.of(2026, 8, 24, 0, 0, 0),
                LocalDateTime.of(2025, 8, 24, 0, 0, 0),
                100,
                20
        );
        Fruit orange = new Fruit(
                "Orange",
                "Spain",
                LocalDateTime.of(2025, 8, 23, 0, 0, 0),
                LocalDateTime.of(2025, 8, 10, 0, 0, 0),
                80,
                15
        );
        Fruit banana = new Fruit(
                "Banana",
                "Ecuador",
                LocalDateTime.of(2025, 9, 10, 0, 0, 0),
                LocalDateTime.of(2025, 8, 10, 0, 0, 0),
                90,
                18
        );
        AbstractStore shop = new Shop(new ArrayList<>(List.of(apple)));
        AbstractStore wareHouse = new Warehouse(new ArrayList<>(List.of(orange)));
        AbstractStore trash = new Trash(new ArrayList<>(List.of(banana)));
        this.stores = List.of(shop, wareHouse, trash);
        ControlQuality control = new ControlQuality(stores, List.of());
        control.resort();
        assertThat(shop.findAll()).contains(banana);
        assertThat(wareHouse.findAll()).contains(apple);
        assertThat(trash.findAll()).contains(orange);
    }
}