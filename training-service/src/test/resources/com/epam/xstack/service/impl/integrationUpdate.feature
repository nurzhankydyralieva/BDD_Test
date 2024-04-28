Feature: Updating Trainer Workload

  Scenario: Updating trainer workload successfully
    Given A trainer with valid information
    When The update trainer workload method is called
    Then The trainer workload should be updated successfully