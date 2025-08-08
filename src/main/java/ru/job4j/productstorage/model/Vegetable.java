package ru.job4j.productstorage.model;

import java.time.LocalDateTime;

public class Vegetable extends Food {
    public Vegetable(String name, String countryOrigin, LocalDateTime expiryDate, LocalDateTime createDate,
                     double price, double discount) {
        super(name, countryOrigin, expiryDate, createDate, price, discount);
    }
}
