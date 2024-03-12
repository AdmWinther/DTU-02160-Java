Feature: check in with a travel card
  Scenario: Successful check in
    Given A travel card with balance of 100
    And Check in status false
    And Check in machine at "Times Square St."
    When Check in
    Then Check in machine should return a response message that the card is checked in.

  Scenario: Already checked in
    Given A travel card with balance of 100
    And Check in status true
    And Check in machine at "Times Square St."
    When Check in
    Then Check in machine should return a response message that the card is already checked in.

  Scenario: Not enough credit to check in
    Given A travel card with balance of 5
    And Check in status false
    And Check in machine at "Times Square St."
    When Check in
    Then Check in machine should return a response message that the balance is not enough.
