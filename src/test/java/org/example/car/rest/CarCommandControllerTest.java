package org.example.car.rest;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import io.zonky.test.db.postgres.embedded.FlywayPreparer;
import io.zonky.test.db.postgres.junit.EmbeddedPostgresRules;
import io.zonky.test.db.postgres.junit.PreparedDbRule;
import io.zonky.test.db.postgres.junit.SingleInstancePostgresRule;
import org.example.car.CarEntity;
import org.example.domain.repositories.CarRepository;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureEmbeddedDatabase(provider = ZONKY)
public class CarCommandControllerTest {

    @Rule
    public SingleInstancePostgresRule pg = EmbeddedPostgresRules.singleInstance();

    @Rule
    public PreparedDbRule db =
            EmbeddedPostgresRules.preparedDatabase(
                    FlywayPreparer.forClasspathLocation());

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarCommandController carCommandController;

    @Mock
    private CarRepository carRepository;

    @LocalServerPort
    private int port;

    @Test
    void testCreateCar() throws Exception {
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

    @Test
    void testUpdateNameCar() throws Exception {

        Long id = 1L;

        CarEntity carEntity = new CarEntity();
        carEntity.setOwner("Mr. Toto");
        carEntity.setName("BMW");
        carEntity.setAmount(BigDecimal.valueOf(1000000));
        when(carRepository.findById(id)).thenReturn(Optional.of(carEntity));

        mockMvc.perform(
                        post("/car/update-name")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{ \"id\": 1, \"owner\": \"Mr. Tata\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.version").value(0))
                .andExpect(jsonPath("$.name").value("BMW"))
                .andExpect(jsonPath("$.owner").value("Mr. Toto"))
                .andExpect(jsonPath("$.amount").value(100000));
    }
}
