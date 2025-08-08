package ru.job4j.productstorage.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Food {
    private String name;
    private String countryOrigin;
    private LocalDateTime expiryDate;
    private LocalDateTime createDate;
    private double price;
    private double discount;

    public Food(String name, String countryOrigin, LocalDateTime expiryDate, LocalDateTime createDate,
                double price, double discount) {
        this.name = name;
        this.countryOrigin = countryOrigin;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public void setCountryOrigin(String countryOrigin) {
        this.countryOrigin = countryOrigin;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Food food = (Food) object;
        return Objects.equals(name, food.name) && Objects.equals(countryOrigin, food.countryOrigin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, countryOrigin);
    }
}
