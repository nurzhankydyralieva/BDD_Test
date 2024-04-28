Feature: Deleting a Trainer

  Scenario: Successfully delete a trainer
    Given A trainer exists with id "662e3b8467e78244f8e7efb6"
    When The deleteTrainer method is called with id "662e3b8467e78244f8e7efb6"
    Then The response should indicate successful deletion of the trainer