package org.afs.pakinglot.domain;

import org.afs.pakinglot.domain.strategies.AvailableRateStrategy;
import org.afs.pakinglot.domain.strategies.MaxAvailableStrategy;
import org.afs.pakinglot.domain.strategies.ParkingStrategy;
import org.afs.pakinglot.domain.strategies.SequentiallyStrategy;

import java.util.List;

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
    }

    public List<ParkingLot> getParkingLots() {
        return List.of(plazaPark, cityMallGarage, officeTowerParking);
    }

    public List<ParkingBoy> getParkingBoys() {
        return List.of(standardParkingBoy, smartParkingBoy, superSmartParkingBoy);
    }

    public Ticket park(ParkingStrategy strategy, Car car) {
        ParkingBoy parkingBoy = switch (strategy.getClass().getSimpleName()) {
            case "SequentiallyStrategy" -> standardParkingBoy;
            case "MaxAvailableStrategy" -> smartParkingBoy;
            case "AvailableRateStrategy" -> superSmartParkingBoy;
            default -> throw new IllegalArgumentException("Unknown parking strategy");
        };
        return parkingBoy.park(car);
    }
}
