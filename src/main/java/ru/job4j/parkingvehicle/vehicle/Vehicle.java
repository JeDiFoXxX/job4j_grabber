package ru.job4j.parkingvehicle.vehicle;

import ru.job4j.parkingvehicle.parking.AbstractParking;

import java.util.List;
import java.util.Objects;

public abstract class Vehicle {
    private static List<? extends AbstractParking> strategy;
    private final int size;
    private AbstractParking parking;
    private int indexParking = -1;

    protected Vehicle(int size) {
        this.size = size;
    }

    public static List<? extends AbstractParking> getStrategy() {
        return strategy;
    }

    public static void setStrategy(List<? extends AbstractParking> list) {
        strategy = list;
    }

    public int getSize() {
        return size;
    }

    public AbstractParking getParking() {
        return parking;
    }

    public void setParking(AbstractParking parking) {
        this.parking = parking;
    }

    public int getIndexParking() {
        return indexParking;
    }

    public void setIndexParking(int indexParking) {
        this.indexParking = indexParking;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Vehicle vehicle = (Vehicle) object;
        return indexParking == vehicle.indexParking && Objects.equals(parking, vehicle.parking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parking, indexParking);
    }
}
