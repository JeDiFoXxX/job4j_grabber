package ru.job4j.parkingvehicle.parking;

import ru.job4j.parkingvehicle.vehicle.Vehicle;

public class PasCarParking extends AbstractParking {
    public PasCarParking(int size) {
        super(size);
    }

    @Override
    public int checkPlace(int size) {
        return -1;
    }

    @Override
    public void add(Vehicle vehicle) { }

    @Override
    public void remove(Vehicle vehicle) { }
}
