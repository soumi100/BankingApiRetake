Feature: transaction

  Background:
    Given User is Logged in
    And visiting http://localhost:8484/transactions

  Scenario: Get transactions by IBAN
    When I retrieve all transactions by IBAN
    Then I get http status 200

  Scenario: Get transactions
    When I retrieve all transactions
    Then I get http status 200

  Scenario: Create transaction
    Given User is creating a new transaction
    Then I get http status 201

