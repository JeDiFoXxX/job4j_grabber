package ru.job4j.parkingvehicle.parking;

import ru.job4j.parkingvehicle.vehicle.Vehicle;

public class PasCarParking extends AbstractParking {
    public PasCarParking(int size) {
        super(size);
    }

    @Override
    public int checkPlace(int size) {
        int indexParking = -1;
        for (int i = 0; i < getVehicles().length; i++) {
            if (!getVehicles()[i]) {
                int count = 0;
                int finish = i + size - 1;
                while (finish < getVehicles().length && i < finish) {
                    if (getVehicles()[finish]) {
                        break;
                    }
                    count++;
                    finish--;
                }
                if (count == size - 1) {
                    indexParking = i;
                    break;
                }
            }
        }
        return indexParking;
    }

    @Override
    public void add(Vehicle o, int indexParking) {
        int length = indexParking + o.getSize();
        for (int i = indexParking; i < length; i++) {
            getVehicles()[i] = true;
        }
    }

    @Override
    public void remove(Vehicle o) {
        int length = o.getIndexParking() + o.getSize();
        for (int i = o.getIndexParking(); i < length; i++) {
            getVehicles()[i] = false;
        }
    }
}
