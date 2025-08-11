package ru.job4j.parkingvehicle.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.parkingvehicle.vehicle.PasCar;
import ru.job4j.parkingvehicle.vehicle.Truck;
import ru.job4j.parkingvehicle.vehicle.Vehicle;

import static org.assertj.core.api.Assertions.*;

@Disabled
class AbstractParkingTest {
    @Test
    void whenPasCarParkingHasFreePlaceThenReturnZero() {
        AbstractParking parking = new PasCarParking(1);
        Vehicle vehicle = new PasCar();
        assertThat(parking.checkPlace(vehicle.getSize())).isEqualTo(0);
    }

    @Test
    void whenPasCarParkingPlaceOccupiedThenReturnMinusOne() {
        AbstractParking parking = new PasCarParking(1);
        parking.getVehicles()[0] = true;
        Vehicle vehicle = new PasCar();
        assertThat(parking.checkPlace(vehicle.getSize())).isEqualTo(-1);
    }

    @Test
    void whenTruckParkingHasOccupiedPlaceThenFindNextFreePlace() {
        AbstractParking parking = new TruckParking(2);
        parking.getVehicles()[0] = true;
        Truck truck = new Truck(2);
        assertThat(parking.checkPlace(truck.getSize())).isEqualTo(1);
    }

    @Test
    void whenTruckParkingHasNoSpaceThenReturnMinusOne() {
        AbstractParking parking = new TruckParking(1);
        parking.getVehicles()[0] = true;
        Truck truck = new Truck(2);
        assertThat(parking.checkPlace(truck.getSize())).isEqualTo(-1);
    }

    @Test
    void whenPasCarParkingHasEnoughSpaceForTruckThenReturnStartIndex() {
        AbstractParking parking = new PasCarParking(2);
        Truck truck = new Truck(2);
        assertThat(parking.checkPlace(truck.getSize())).isEqualTo(0);
    }

    @Test
    void whenPasCarParkingHasNotEnoughSpaceForTruckThenReturnMinusOne() {
        AbstractParking parking = new PasCarParking(1);
        Truck truck = new Truck(2);
        assertThat(parking.checkPlace(truck.getSize())).isEqualTo(-1);
    }

    @Test
    void whenAddPasCarThenPlaceIsOccupied() {
        AbstractParking parking = new PasCarParking(2);
        Vehicle vehicle = new PasCar();
        parking.add(vehicle);
        assertThat(parking.getVehicles()[0]).isTrue();
    }

    @Test
    void whenAddTruckThenMultiplePlacesAreOccupied() {
        AbstractParking parking = new PasCarParking(2);
        Vehicle vehicle = new Truck(2);
        parking.add(vehicle);
        assertThat(parking.getVehicles()[0]).isTrue();
        assertThat(parking.getVehicles()[1]).isTrue();
    }

    @Test
    void whenAddTruckToTruckParkingThenPlaceIsOccupied() {
        AbstractParking parking = new TruckParking(1);
        Vehicle vehicle = new Truck(2);
        parking.add(vehicle);
        assertThat(parking.getVehicles()[0]).isTrue();
    }

    @Test
    void whenRemovePasCarThenPlaceIsFreed() {
        AbstractParking parking = new PasCarParking(2);
        Vehicle vehicle = new PasCar();
        parking.add(vehicle);
        parking.remove(vehicle);
        assertThat(parking.getVehicles()[0]).isFalse();
    }

    @Test
    void whenRemoveTruckThenMultiplePlacesAreFreed() {
        AbstractParking parking = new PasCarParking(2);
        Vehicle vehicle = new Truck(2);
        parking.add(vehicle);
        parking.remove(vehicle);
        assertThat(parking.getVehicles()[0]).isFalse();
        assertThat(parking.getVehicles()[1]).isFalse();
    }

    @Test
    void whenRemoveTruckFromTruckParkingThenPlaceIsFreed() {
        AbstractParking parking = new TruckParking(1);
        Vehicle vehicle = new Truck(2);
        parking.add(vehicle);
        parking.remove(vehicle);
        assertThat(parking.getVehicles()[0]).isFalse();
    }
}