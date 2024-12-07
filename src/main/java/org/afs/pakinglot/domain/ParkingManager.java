package org.afs.pakinglot.domain;

import org.afs.pakinglot.domain.exception.CarNotFoundException;
import org.afs.pakinglot.domain.strategies.AvailableRateStrategy;
import org.afs.pakinglot.domain.strategies.MaxAvailableStrategy;
import org.afs.pakinglot.domain.strategies.ParkingStrategy;
import org.afs.pakinglot.domain.strategies.SequentiallyStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParkingManager {
    private final ParkingLot plazaPark;
    private final ParkingLot cityMallGarage;
    private final ParkingLot officeTowerParking;
    private final ParkingBoy standardParkingBoy;
    private final ParkingBoy smartParkingBoy;
    private final ParkingBoy superSmartParkingBoy;

    public ParkingManager() {
        plazaPark = new ParkingLot(1, "The Plaza Park", 9);
        cityMallGarage = new ParkingLot(2, "City Mall Garage", 12);
        officeTowerParking = new ParkingLot(3, "Office Tower Parking", 9);

        List<ParkingLot> parkingLots = List.of(plazaPark, cityMallGarage, officeTowerParking);

        standardParkingBoy = new ParkingBoy(parkingLots, new SequentiallyStrategy());
        smartParkingBoy = new ParkingBoy(parkingLots, new MaxAvailableStrategy());
        superSmartParkingBoy = new ParkingBoy(parkingLots, new AvailableRateStrategy());

        // Initial data
        plazaPark.park(new Car("ABC123"));
        cityMallGarage.park(new Car("DEF456"));
        officeTowerParking.park(new Car("GHI789"));
    }

    public List<ParkingLot> getParkingLots() {
        return List.of(plazaPark, cityMallGarage, officeTowerParking);
    }

    public List<ParkingBoy> getParkingBoys() {
        return List.of(standardParkingBoy, smartParkingBoy, superSmartParkingBoy);
    }

    public Ticket park(String strategy, Car car) {
      return switch (strategy) {
            case "NORMAL" -> standardParkingBoy.park(car);
            case "SMART" -> smartParkingBoy.park(car);
            case "SUPER" -> superSmartParkingBoy.park(car);
            default -> throw new IllegalArgumentException("Unknown parking strategy");
        };
    }

    public Car fetch(String carPlate) {
        return getParkingLots().stream()
                .map(parkingLot -> parkingLot.fetchCar(carPlate))
                .filter(car -> car != null)
                .findFirst()
                .orElseThrow(() -> new CarNotFoundException(carPlate));
    }
}
