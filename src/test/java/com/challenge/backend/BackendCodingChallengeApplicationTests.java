package com.challenge.backend;

import com.challenge.backend.dao.UserRepository;
import com.challenge.backend.entities.User;
import com.challenge.backend.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Application test class
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BackendCodingChallengeApplicationTests {

    /**
     * bind the above RANDOM_PORT
     */
    @LocalServerPort
    private int port;

    /**
     * Inject instance of TestRestTemplate
     */
    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Inject instance of MockMvc
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * HttpHeader class property
     */
    HttpHeaders headers = new HttpHeaders();

    /**
     * Inject instance of user service
     */
    @Autowired
    private UserService userService;

    /**
     * Required Mock bean in user service
     */
    @MockBean
    private UserRepository userRepository;

    /**
     * Required Mock bean in user service
     */
    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Testing rest controller home end point method
     */
    @Test
    void restApiHomePageTest() throws Exception {
        // entity object
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        // send get method
        ResponseEntity<String> response = this.restTemplate.exchange(
                createURLWithPort("/rest/v1/"), HttpMethod.GET, entity, String.class);
        // Retrieve response content
        String content = response.getBody();
        // Body not null assertion
        assert content != null;
        // Body content assertion
        assertTrue(content.contains("Hello world, server is working properly"));
    }

    /**
     * Testing user service and repository method
     */
    @Test
    void testRetrieveUserWithUserRepository() throws Exception {
        String email = "Haythaminfo99@gmail.com";
        // Create a new user optional object
        Optional<User> optUser = Optional.of(new User(null, "haythamdahri", email, this.bCryptPasswordEncoder.encode("toortoor"), true, null, null));
        // Retrieve the actual user from database using DAO accessor
        when(this.userRepository.findByEmail(email)).thenReturn(optUser);
        // Set assertion condition
        assertTrue(this.userService.getUser(email).getEmail().contains("Haythaminfo99"));
    }

    /**
     * Testing rest api end point by expecting the returned status
     */
    @Test
    public void testRestApiEndPoints() throws Exception{
        // Test home end point
        this.mockMvc.perform(get("/rest/v1/")).andExpect(status().is2xxSuccessful());
        // Test /save-user end point with get method
        this.mockMvc.perform(get("/rest/v1/save-user")).andExpect(status().isMethodNotAllowed());
        // Test /save-user end point with post method
        this.mockMvc.perform(post("/rest/v1/save-user")).andExpect(status().is4xxClientError());
    }

    /**
     * Construct url for internal use
     */
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
