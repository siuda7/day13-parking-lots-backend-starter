package org.afs.pakinglot.controller;

import org.afs.pakinglot.domain.ParkingLot;
import org.afs.pakinglot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("parkinglot")
public class ParkingLotController {

    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @GetMapping
    public List<ParkingLot> getAllParkingLots() {
        return parkingLotService.getAllParkingLots();
    }
}