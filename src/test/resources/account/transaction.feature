Feature: transaction

  Background:
    Given http://localhost:8484/login
    And Do login as employee
    And visiting http://localhost:8484/transactions

  Scenario: Get transactions by IBAN
    Given account iban is set in the url
    Then I get http status 200

  Scenario: Get transactions
    Given the http verb is GET
    And limit is set (optional)
    Then I get http status 200

  Scenario: Create transaction
    Given the http verb is Post
    And account is set in the request body
    Then I get http status 201
