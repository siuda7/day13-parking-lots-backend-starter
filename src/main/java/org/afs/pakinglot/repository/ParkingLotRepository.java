package org.afs.pakinglot.repository;

import org.afs.pakinglot.domain.ParkingLot;
import org.afs.pakinglot.domain.ParkingManager;
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
}