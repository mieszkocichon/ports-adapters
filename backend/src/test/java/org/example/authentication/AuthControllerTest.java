package org.example.authentication;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import io.zonky.test.db.postgres.embedded.FlywayPreparer;
import io.zonky.test.db.postgres.junit.EmbeddedPostgresRules;
import io.zonky.test.db.postgres.junit.PreparedDbRule;
import io.zonky.test.db.postgres.junit.SingleInstancePostgresRule;
import org.example.user.UserRepository;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = MOCK)
@AutoConfigureEmbeddedDatabase(provider = ZONKY)
class AuthControllerTest {

    @Rule
    public SingleInstancePostgresRule pg = EmbeddedPostgresRules.singleInstance();

    @Rule
    public PreparedDbRule db =
            EmbeddedPostgresRules.preparedDatabase(
                    FlywayPreparer.forClasspathLocation());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @CsvSource({
            "test,te@st.pl,pass,size must be between 6 and 40",
            "test,te@st.pl,,size must be between 6 and 40",
            "test,te@st.pl,12345,size must be between 6 and 40",
            "test,te@st.pl,verylongpasswordthatexceedsthemaximumlength,size must be between 6 and 40"
    })
    public void testRegisterUser_Failure(String username, String email, String password, String expectedErrorMessage) throws Exception {

        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{ \"username\": \"%s\", \"email\": \"%s\", \"password\": \"%s\"}", username, email, password)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.password").value(expectedErrorMessage));

    }

    @Test
    void testRegisterUser_Success() throws Exception {
        mockMvc.perform(
                        post("/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{ \"username\": \"username\", \"email\": \"s@s.pl\", \"password\": \"password\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("username"))
                .andExpect(jsonPath("$.email").value("s@s.pl"));
    }

    @Test
    void testSignInUser_Success() throws Exception {

        mockMvc.perform(
                        post("/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{ \"username\": \"username1\", \"email\": \"s@s1.pl\", \"password\": \"password\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("username1"))
                .andExpect(jsonPath("$.email").value("s@s1.pl"));

        mockMvc.perform(
                post("/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"username1\", \"email\": \"s@s1.pl\", \"password\": \"password\"}")
        ).andExpect(status().isOk());
    }
}