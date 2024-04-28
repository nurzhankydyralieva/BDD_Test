package com.epam.xstack.service.impl;

import com.epam.xstack.model.dto.TrainerResponseOkDTO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IntegrationDeleteFeaturesSteps {
    @LocalServerPort
    private final String baseUrl = "http://localhost";
    private int port = 8084;
    private ResponseEntity<TrainerResponseOkDTO> deleteResponse;
    private String trainerId;

    @Given("A trainer exists with id {string}")
    public void a_trainer_exists_with_id(String id) {
        this.trainerId = id;
    }

    @When("The deleteTrainer method is called with id {string}")
    public void the_delete_trainer_method_is_called_with_id(String id) {
        String url = baseUrl + ":" + port + "/api/trainer";
        RestTemplate restTemplate = new RestTemplate();
        this.deleteResponse = restTemplate.exchange(url + "/" + "{id}", HttpMethod.DELETE, null, TrainerResponseOkDTO.class, id);
    }

    @Then("The response should indicate successful deletion of the trainer")
    public void the_response_should_indicate_successful_deletion_of_the_trainer() {
        assertEquals(HttpStatus.OK, deleteResponse.getStatusCode());
    }

}
