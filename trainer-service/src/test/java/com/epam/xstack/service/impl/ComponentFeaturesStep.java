package com.epam.xstack.service.impl;

import com.epam.xstack.model.dto.TrainerRequestDTO;
import com.epam.xstack.model.dto.TrainerResponseOkDTO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Cucumber.class)
public class ComponentFeaturesStep {
    private int trainerInitSize;
    private TrainerResponseOkDTO savedTrainer;
    private List<TrainerRequestDTO> trainerList = new ArrayList<>();

    @Given("I have a static method which initialize a list of trainer")
    public void i_have_a_static_method_which_initialize_a_list_of_trainer() {
        initTrainerList();
    }

    @When("I can see the size of the initialized list of trainer")
    public void i_can_see_the_initialized_list_of_trainer() {
        trainerInitSize = trainerList.size();
    }

    @Then("The list size is equal to 3")
    public void the_list_size_is_equal_to_3() {
        assertEquals(3, trainerList.size());
    }

    @Given("The list of trainer contains 3 trainers already stored")
    public void the_list_of_trainer_contains_3_trainers_already_stored() {
        initTrainerList();
        trainerInitSize = trainerList.size();
    }

    @When("I create a new trainer with random entire")
    public void i_create_a_new_trainer_with_random_entire() {
        TrainerRequestDTO newTrainer = new TrainerRequestDTO();
        newTrainer.setFirstName("John");
        newTrainer.setLastName("Doe");
        savedTrainer = createTrainer(newTrainer);

        trainerInitSize = trainerList.size();
    }

    @Then("I get the ID of the new trainer and the list contains more than 3 trainers")
    public void i_get_the_ID_of_the_new_trainer_and_the_list_contains_more_than_3_trainers() {
        assertEquals(4, trainerInitSize);
        assertNotNull(savedTrainer);
    }

    public List<TrainerRequestDTO> initTrainerList() {
        trainerList.add(new TrainerRequestDTO());
        trainerList.add(new TrainerRequestDTO());
        trainerList.add(new TrainerRequestDTO());
        return trainerList;
    }

    private TrainerResponseOkDTO createTrainer(TrainerRequestDTO trainerRequestDTO) {
        trainerList.add(trainerRequestDTO);
        return TrainerResponseOkDTO.builder().build();
    }
}
