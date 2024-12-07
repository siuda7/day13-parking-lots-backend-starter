package org.afs.pakinglot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.afs.pakinglot.domain.Car;
import org.afs.pakinglot.domain.ParkCarRequest;
import org.afs.pakinglot.domain.ParkingLot;
import org.afs.pakinglot.domain.Ticket;
import org.afs.pakinglot.domain.exception.CarNotFoundException;
import org.afs.pakinglot.repository.ParkingLotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
class ParkingLotControllerTest {

    @Autowired
    private MockMvc client;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private ParkingLotRepository parkingLotRepository;

    private List<ParkingLot> parkingLots;

    @BeforeEach
    void setUp() {
        parkingLots = Arrays.asList(
                new ParkingLot(1, "The Plaza Park", 9),
                new ParkingLot(2, "City Mall Garage", 12),
                new ParkingLot(3, "Office Tower Parking", 9)
        );
    }

    @Test
    void given_park_car_request_when_get_to_park_then_return_ticket() throws Exception {
        // Given
        given(parkingLotRepository.findAll()).willReturn(parkingLots);

        // When
        client.perform(get("/parkinglot"))
                // Then
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("The Plaza Park"))
                .andExpect(jsonPath("$[0].capacity").value(9))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("City Mall Garage"))
                .andExpect(jsonPath("$[1].capacity").value(12))
                .andExpect(jsonPath("$[2].id").value(3))
                .andExpect(jsonPath("$[2].name").value("Office Tower Parking"))
                .andExpect(jsonPath("$[2].capacity").value(9));
    }

    @ParameterizedTest
    @CsvSource({
            "SUPER, 1, 1",
            "NORMAL, 2, 1",
            "SMART, 1, 2"
    })
    void given_park_car_request_when_post_to_park_then_return_ticket(String strategy, Integer position, Integer parkingLot) throws Exception {
        // Given
        Car car = new Car("ABC123");
        String carPlate = "ABC123";
        Ticket ticket = new Ticket("ABC123", position, parkingLot);

        given(parkingLotRepository.parkCar(strategy, car)).willReturn(ticket);

        ParkCarRequest request = new ParkCarRequest(strategy, carPlate );

        // When
        client.perform(post("/parkinglot/park")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                // Then
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(ticket)));
    }

    @Test
    void given_existing_car_plate_when_get_to_fetch_then_return_car() throws Exception {
        // Given
        String carPlate = "ABC123";
        Car car = new Car(carPlate);

        given(parkingLotRepository.fetchCar(carPlate)).willReturn(car);

        // When
        client.perform(get("/parkinglot/fetch/{carPlate}", carPlate))
                // Then
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.plateNumber").value(carPlate));
    }
}