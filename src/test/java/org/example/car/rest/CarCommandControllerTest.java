package org.example.car.rest;

import org.example.car.CarEntity;
import org.example.domain.repositories.CarRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CarCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarCommandController carCommandController;

    @Mock
    private CarRepository carRepository;

    @Test
    void testGetData() throws Exception {

        mockMvc.perform(
                post("/car/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"BMW\", \"owner\": \"Mr. Toto\", \"amount\": 100000 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.version").value(0))
                .andExpect(jsonPath("$.name").value("BMW"))
                .andExpect(jsonPath("$.owner").value("Mr. Toto"))
                .andExpect(jsonPath("$.amount").value(100000));
    }
}