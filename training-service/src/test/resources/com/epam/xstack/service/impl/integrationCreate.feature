Feature: Creating Trainer

  Scenario: Creating a Trainer successfully
    Given A Trainer request with valid data
    When The createTrainer endpoint is called
    Then The response status should be 201

  Scenario: Creating a Trainer with invalid data
    Given A Trainer request with invalid data
    When The createTrainer endpoint is called
    Then The response status should indicate failure
