Feature: Users

  Background:
    Given http://localhost:8484/login
    And Do login as user as employee
    And visiting users endpoint

  Scenario: Get Users
    Given the http verb is GET
    And  limit is set
    Then return a list of users

  Scenario: Create User
    Given the http verb is Post
    And user is set in the request body
    Then user should be created

  Scenario: Get User by ID
    Given the user id is set in the url
    Then display the user matching the given id