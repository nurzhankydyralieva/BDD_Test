package com.epam.xstack.service.impl;

import com.epam.xstack.model.dto.TrainerDTO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IntegrationCreateFeaturesSteps {
    @LocalServerPort
    private final String baseUrl = "http://localhost";
    private int port = 8084;
    private ResponseEntity<TrainerDTO> responseEntity;

    @Given("A Trainer request with valid data")
    public void a_trainer_request_with_valid_data() {
        String url = baseUrl + ":" + port + "/api/trainer";
        TrainerDTO requestDTO = new TrainerDTO();
        requestDTO.setFirstName("Marlyn");
        requestDTO.setLastName("Monro");
        requestDTO.setUserName("Mary_Toms");

        RestTemplate restTemplate = new RestTemplate();
        responseEntity = restTemplate.postForEntity(url, requestDTO, TrainerDTO.class);
    }

    @When("The createTrainer endpoint is called")
    public void the_create_trainer_endpoint_is_called() {
        log.info("The trainer endpoint is called");
    }

    @Then("The response status should be 201")
    public void the_response_status_should_be_201() {
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Given("A Trainer request with invalid data")
    public void a_trainer_request_with_invalid_data() {
        String url = baseUrl + ":" + port + "/api/trainer";
        TrainerDTO requestDTO = new TrainerDTO();
        requestDTO.setFirstName("test");
        requestDTO.setLastName("test");

        RestTemplate restTemplate = new RestTemplate();
        responseEntity = restTemplate.postForEntity(url, requestDTO, TrainerDTO.class);
    }

    @Then("The response status should indicate failure")
    public void the_response_status_should_indicate_failure() {
        assertNotEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
