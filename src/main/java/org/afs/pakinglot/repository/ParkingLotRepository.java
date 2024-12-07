package org.afs.pakinglot.repository;

import org.afs.pakinglot.domain.Car;
import org.afs.pakinglot.domain.ParkingLot;
import org.afs.pakinglot.domain.ParkingManager;
import org.afs.pakinglot.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParkingLotRepository {

    private final ParkingManager parkingManager;

    public ParkingLotRepository(ParkingManager parkingManager) {
        this.parkingManager = parkingManager;
    }

    public List<ParkingLot> findAll() {
        return parkingManager.getParkingLots();
    }

    public Ticket parkCar(String strategy, Car car) {
        return parkingManager.park(strategy, car);
    }

    public Car fetchCar(String carPlate) {
        return parkingManager.fetch(carPlate);
    }
}