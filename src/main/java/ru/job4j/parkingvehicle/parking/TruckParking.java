package ru.job4j.parkingvehicle.parking;

import ru.job4j.parkingvehicle.vehicle.Vehicle;

public class TruckParking extends AbstractParking {
    public TruckParking(int size) {
        super(size);
    }

    @Override
    public int checkPlace(int size) {
        int indexParking = -1;
        for (int i = 0; i < getVehicles().length; i++) {
            if (!getVehicles()[i]) {
                indexParking = i;
                break;
            }
        }
        return indexParking;
    }

    @Override
    public void add(Vehicle o, int indexParking) {
        getVehicles()[indexParking] = true;
    }

    @Override
    public void remove(Vehicle o) {
        getVehicles()[o.getIndexParking()] = false;
    }
}
