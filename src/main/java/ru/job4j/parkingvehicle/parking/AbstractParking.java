package ru.job4j.parkingvehicle.parking;

import ru.job4j.parkingvehicle.vehicle.Vehicle;

public abstract class AbstractParking implements Parking<Vehicle> {
    private final boolean[] vehicles;

    public AbstractParking(int size) {
        this.vehicles = new boolean[size];
    }

    public boolean[] getVehicles() {
        return vehicles;
    }

    public abstract int checkPlace(int size);

    public abstract void add(Vehicle vehicle);

    public abstract void remove(Vehicle vehicle);
}
