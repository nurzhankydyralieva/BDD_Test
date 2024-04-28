package com.epam.xstack.service.impl;

import com.epam.xstack.model.dto.TrainerRequestDTO;
import com.epam.xstack.model.dto.TrainerResponseDTO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IntegrationUpdateFeaturesSteps {
    @LocalServerPort
    private final String baseUrl = "http://localhost";
    private int port = 8084;
    private ResponseEntity<TrainerResponseDTO> responseEntity;
    private TrainerRequestDTO trainerRequest;

    @Given("A trainer with valid information")
    public void a_trainer_with_valid_information() {
        trainerRequest = createValidTrainerRequest();
    }

    @When("The update trainer workload method is called")
    public void the_update_trainer_workload_method_is_called() {
        RestTemplate restTemplate = new RestTemplate();
        String url = baseUrl + ":" + port + "/api/trainer";
        TrainerRequestDTO request = new TrainerRequestDTO();
        try {
            restTemplate.postForObject(url , request, TrainerResponseDTO.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            e.printStackTrace();
        }
    }

    @Then("The trainer workload should be updated successfully")
    public void the_trainer_workload_should_be_updated_successfully() {
        // Add assertions to verify the update is successful
    }

    private TrainerRequestDTO createValidTrainerRequest() {
        TrainerRequestDTO request = new TrainerRequestDTO();
        request.setFirstName("Andrea");
        request.setLastName("Bocelli");
        request.setUserName("Andrea.Bocelli");
        request.setIsActive(true);
        request.setTrainingDuration(60);
        request.setYears(Collections.singletonList(3));
        request.setMonths(Collections.singletonList("June"));
        return request;
    }

}
