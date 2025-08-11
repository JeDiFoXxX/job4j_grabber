package ru.job4j.parkingvehicle.control;

import ru.job4j.parkingvehicle.parking.AbstractParking;
import ru.job4j.parkingvehicle.vehicle.Vehicle;

import java.util.List;

public class ControlParking {
    private final List<? extends AbstractParking> list;

    public ControlParking(List<? extends AbstractParking> list) {
        this.list = list;
    }

    public void addVehicle(Vehicle vehicle) { }

    public void removeVehicle(Vehicle vehicle) { }
}
