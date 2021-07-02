Feature: account

  Background:
    Given http://localhost:8484/login
    And Do login as employee
    And visiting accounts endpoint

  Scenario: Get Account by IBAN
    Given account iban is set in the url
    Then display the account matching the given iban

  Scenario: Get accounts
    Given the http verb is GET
    And limit is set (optional)
    Then return a list of all accounts

  Scenario: Create Account
    Given the http verb is Post
    And account is set in the request body
    Then save the account to the database

  Scenario: Delete Account
    When the http verb is Delete
    And the account iban is set in the url
    Then delete the account from the database