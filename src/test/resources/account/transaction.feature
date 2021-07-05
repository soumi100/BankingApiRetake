Feature: transaction

  Background:
    Given http://localhost:8484/login
    And Do login as employee
    And visiting http://localhost:8484/transactions

  Scenario: Get transactions by IBAN
    Given account iban is set in the url
    Then display the account matching the given iban

  Scenario: Get transactions
    Given the http verb is GET
    And limit is set (optional)
    Then return a list of all accounts

  Scenario: Create transaction
    Given the http verb is Post
    And account is set in the request body
    Then save the account to the database
