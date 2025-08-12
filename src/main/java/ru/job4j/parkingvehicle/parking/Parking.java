package ru.job4j.parkingvehicle.parking;

public interface Parking<T> {
    int checkPlace(int size);

    void add(T o, int indexParking);

    void remove(T o);
}
