Feature: account

  Background:
    Given http://localhost:8484/login
    And Do login
    And visiting accounts endpoint

  Scenario: Get Account by IBAN
    Given account iban is set in the url
    Then display the account matching the given iban

  Scenario: Get accounts
    And the http verb is GET
    And limit is set (optional)
    Then return a list of all accounts