package ru.job4j.parkingvehicle.control;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.parkingvehicle.parking.AbstractParking;
import ru.job4j.parkingvehicle.parking.PasCarParking;
import ru.job4j.parkingvehicle.parking.TruckParking;
import ru.job4j.parkingvehicle.vehicle.PasCar;
import ru.job4j.parkingvehicle.vehicle.Truck;
import ru.job4j.parkingvehicle.vehicle.Vehicle;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled
class ControlParkingTest {
    @Test
    void whenAddPasCarThenAssignParkingAndOccupyPlace() {
        AbstractParking parking = new PasCarParking(10);
        ControlParking controlParking = new ControlParking(
                Map.of(PasCar.class, List.of(parking))
        );
        Vehicle vehicle = new PasCar();
        controlParking.addVehicle(vehicle);
        assertThat(vehicle.getParking()).isEqualTo(parking);
        assertThat(vehicle.getIndexParking()).isEqualTo(0);
        assertThat(parking.getVehicles()[0]).isTrue();
    }

    @Test
    void whenAddTruckOnPasCarParkingThenAssignParkingAndOccupyPlaces() {
        AbstractParking pasCarParking = new PasCarParking(10);
        AbstractParking truckParking = new TruckParking(1);
        ControlParking controlParking = new ControlParking(
                Map.of(PasCar.class, List.of(pasCarParking),
                        Truck.class, List.of(pasCarParking, truckParking))
        );
        Vehicle vehicle = new Truck(2);
        truckParking.getVehicles()[0] = true;
        controlParking.addVehicle(vehicle);
        assertThat(vehicle.getParking()).isEqualTo(pasCarParking);
        assertThat(vehicle.getIndexParking()).isEqualTo(0);
        assertThat(pasCarParking.getVehicles()[0]).isTrue();
        assertThat(pasCarParking.getVehicles()[1]).isTrue();
    }

    @Test
    void whenAddTruckOnTruckParkingThenAssignParkingAndOccupyPlace() {
        AbstractParking parking = new TruckParking(10);
        ControlParking controlParking = new ControlParking(
                Map.of(Truck.class, List.of(parking))
        );
        Vehicle vehicle = new Truck(2);
        controlParking.addVehicle(vehicle);
        assertThat(vehicle.getParking()).isEqualTo(parking);
        assertThat(vehicle.getIndexParking()).isEqualTo(0);
        assertThat(parking.getVehicles()[0]).isTrue();
    }

    @Test
    void whenRemovePasCarThenFreePlaceAndUnsetParking() {
        AbstractParking parking = new PasCarParking(10);
        ControlParking controlParking = new ControlParking(
                Map.of(PasCar.class, List.of(parking))
        );
        Vehicle vehicle = new PasCar();
        controlParking.addVehicle(vehicle);
        controlParking.removeVehicle(vehicle);
        assertThat(vehicle.getParking()).isNull();
        assertThat(vehicle.getIndexParking()).isEqualTo(-1);
        assertThat(parking.getVehicles()[0]).isFalse();
    }

    @Test
    void whenRemoveTruckOnPasCarParkingThenFreePlacesAndUnsetParking() {
        AbstractParking pasCarParking = new PasCarParking(10);
        AbstractParking truckParking = new TruckParking(1);
        ControlParking controlParking = new ControlParking(
                Map.of(PasCar.class, List.of(pasCarParking),
                        Truck.class, List.of(pasCarParking, truckParking))
        );
        Vehicle vehicle = new Truck(2);
        truckParking.getVehicles()[0] = true;
        controlParking.addVehicle(vehicle);
        controlParking.removeVehicle(vehicle);
        assertThat(vehicle.getParking()).isNull();
        assertThat(vehicle.getIndexParking()).isEqualTo(-1);
        assertThat(pasCarParking.getVehicles()[0]).isFalse();
        assertThat(pasCarParking.getVehicles()[1]).isFalse();
    }

    @Test
    void whenRemoveTruckOnTruckParkingThenFreePlaceAndUnsetParking() {
        AbstractParking parking = new TruckParking(10);
        ControlParking controlParking = new ControlParking(
                Map.of(Truck.class, List.of(parking))
        );
        Vehicle vehicle = new Truck(2);
        controlParking.addVehicle(vehicle);
        controlParking.removeVehicle(vehicle);
        assertThat(vehicle.getParking()).isNull();
        assertThat(vehicle.getIndexParking()).isEqualTo(-1);
        assertThat(parking.getVehicles()[0]).isFalse();
    }
}