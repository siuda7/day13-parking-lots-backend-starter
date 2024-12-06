package org.afs.pakinglot.domain;

import org.afs.pakinglot.domain.Car;
import org.afs.pakinglot.domain.ParkingLot;
import org.afs.pakinglot.domain.ParkingManager;
import org.afs.pakinglot.domain.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingManagerTest {

    private ParkingManager parkingManager;

    @BeforeEach
    void setUp() {
        parkingManager = new ParkingManager();
    }

    @Test
    void given_parking_manager_when_get_parking_lots_then_return_all_parking_lots() {
        List<ParkingLot> parkingLots = parkingManager.getParkingLots();
        assertEquals(3, parkingLots.size());
        assertEquals("The Plaza Park", parkingLots.get(0).getName());
        assertEquals("City Mall Garage", parkingLots.get(1).getName());
        assertEquals("Office Tower Parking", parkingLots.get(2).getName());
    }

    @Test
    void given_parking_manager_when_get_parking_boys_then_return_all_parking_boys() {
        List<ParkingBoy> parkingBoys = parkingManager.getParkingBoys();
        assertEquals(3, parkingBoys.size());
    }

    @ParameterizedTest
    @CsvSource({
            "NORMAL, ABC123, 1, 1",
            "SMART, DEF456, 2, 2",
            "SUPER, GHI789, 1, 2 "
    })
    void given_park_car_request_when_park_with_different_strategies_then_return_ticket(String strategy, String carPlate, int expectedLotId, int expectedTicketId) {
        // Given
        Car car = new Car(carPlate);

        // When
        Ticket ticket = parkingManager.park(strategy, car);

        // Then
        assertNotNull(ticket);
        assertEquals(carPlate, ticket.plateNumber());
        assertEquals(expectedLotId, ticket.parkingLot());
    }

    @Test
    void given_park_car_request_when_park_with_unknown_strategy_then_throw_exception() {
        Car car = new Car("JKL012");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            parkingManager.park("UNKNOWN", car);
        });
        assertEquals("Unknown parking strategy", exception.getMessage());
    }
}
