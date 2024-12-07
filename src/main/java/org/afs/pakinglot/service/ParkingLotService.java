package org.afs.pakinglot.service;

import org.afs.pakinglot.domain.Car;
import org.afs.pakinglot.domain.ParkingLot;
import org.afs.pakinglot.domain.Ticket;
import org.afs.pakinglot.domain.strategies.ParkingStrategy;
import org.afs.pakinglot.repository.ParkingLotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {

    private final ParkingLotRepository parkingLotRepository;

    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    public List<ParkingLot> getAllParkingLots() {
        return parkingLotRepository.findAll();
    }

    public Ticket parkCar(String strategy, String carPlate) {
        // Assuming the repository has a method to park the car
        Car car = new Car(carPlate);
        return parkingLotRepository.parkCar(strategy, car);
    }

    public Car fetch(String carPlate) {
        return parkingLotRepository.fetchCar(carPlate);
    }


}