Feature: API Tests


#  Background:  When I get all objects


  Scenario: Validate phone names starting with apple
    When I get all objects
    Then I should see phone names starting with "apple"

  Scenario: Validate phone with lowest price
    When I get all objects
    Then I should see phone with lowest price

  Scenario: Validate device with lowest price
    When I get all objects
    Then I should see device with lowest price

  Scenario: Validate ID is not null
    When I get all objects
    Then I should see all IDs are not null
