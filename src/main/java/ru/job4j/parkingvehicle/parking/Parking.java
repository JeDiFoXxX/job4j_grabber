package ru.job4j.parkingvehicle.parking;

public interface Parking<T> {
    int checkPlace(int size);

    void add(T o);

    void remove(T o);
}
