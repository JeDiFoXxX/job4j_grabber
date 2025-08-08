package ru.job4j.productstorage.model;

import java.time.LocalDateTime;

public class Fruit extends Food {
    public Fruit(String name, String countryOrigin, LocalDateTime expiryDate, LocalDateTime createDate,
                 double price, double discount) {
        super(name, countryOrigin, expiryDate, createDate, price, discount);
    }
}
