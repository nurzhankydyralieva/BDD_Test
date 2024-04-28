Feature: Trainer Service test
  Scenario: Create a new Trainer
    Given I have a static method which initialize a list of trainer
    When I can see the size of the initialized list of trainer
    Then The list size is equal to 3

  Scenario: Creating a new Trainer
    Given The list of trainer contains 3 trainers already stored
    When I create a new trainer with random entire
    Then I get the ID of the new trainer and the list contains more than 3 trainers