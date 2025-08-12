package ru.job4j.parkingvehicle.control;

import ru.job4j.parkingvehicle.parking.AbstractParking;
import ru.job4j.parkingvehicle.vehicle.Vehicle;

import java.util.List;
import java.util.Map;

public class ControlParking {
    private final Map<Class<? extends Vehicle>, List<? extends AbstractParking>> list;

    public ControlParking(Map<Class<? extends Vehicle>, List<? extends AbstractParking>> list) {
        this.list = list;
    }

    public void addVehicle(Vehicle vehicle) {
        if (list.containsKey(vehicle.getClass())) {
            for (AbstractParking parking : list.get(vehicle.getClass())) {
                int indexParking = parking.checkPlace(vehicle.getSize());
                if (indexParking != -1) {
                    parking.add(vehicle, indexParking);
                    vehicle.setParking(parking);
                    vehicle.setIndexParking(indexParking);
                }
            }
        }
    }

    public void removeVehicle(Vehicle vehicle) {
        vehicle.getParking().remove(vehicle);
        vehicle.setParking(null);
        vehicle.setIndexParking(-1);
    }
}
