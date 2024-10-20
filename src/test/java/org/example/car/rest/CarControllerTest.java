package org.example.car.rest;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import io.zonky.test.db.postgres.embedded.FlywayPreparer;
import io.zonky.test.db.postgres.junit.EmbeddedPostgresRules;
import io.zonky.test.db.postgres.junit.PreparedDbRule;
import io.zonky.test.db.postgres.junit.SingleInstancePostgresRule;
import org.example.car.CarCreateRequestToCarEntityAdapter;
import org.example.car.CarEntity;
import org.example.car.CarRepository;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = MOCK)
@AutoConfigureEmbeddedDatabase(provider = ZONKY)
public class CarControllerTest {

    @Rule
    public SingleInstancePostgresRule pg = EmbeddedPostgresRules.singleInstance();

    @Rule
    public PreparedDbRule db =
            EmbeddedPostgresRules.preparedDatabase(
                    FlywayPreparer.forClasspathLocation());

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarController controller;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarCreateRequestToCarEntityAdapter carCreateRequestToCarEntityAdapter;

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
        CreateCarRequest createCarRequest = new CreateCarRequest();
        createCarRequest.setName("BMW2");
        createCarRequest.setOwner("Mr. Toto2");
        createCarRequest.setAmount(10L);
        CarEntity carEntity = carCreateRequestToCarEntityAdapter.map(createCarRequest);
        CarEntity saved = carRepository.save(carEntity);

        mockMvc.perform(post("/car/update-name")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\":" + saved.getId() + ", \"owner\": \"Mr. Toto3\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/car/" + saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.owner").value("Mr. Toto3"));
    }
}
