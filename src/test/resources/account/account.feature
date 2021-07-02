Feature: account

  Background:
    Given http://localhost:8484/login
    And Do login

  Scenario: Get Account by IBAN
    Given account iban is set in the url
    When visiting accounts endpoint
    Then display the account matching the given iban