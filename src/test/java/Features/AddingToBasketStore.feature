Feature: Adding to basket
  Scenario: adding
    Given user is on home page
    When user go into category
    And user choose product
    And user add product to basket
    Then validation name of chosen product