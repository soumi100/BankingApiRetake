Feature: Users

  Background:
    Given http://localhost:8484/login
    And Do login as employee
    And visiting http://localhost:8484/users

  Scenario: Get Users
    Given the http verb is GET
    And  limit is set
    Then return a list of users

  Scenario: Create User
    Given the http verb is Post
    And user is set in the request body
    Then user should be created

  Scenario: Get User by ID
    Given the http verb is Post
    Given the user id is set in the url
    Then display the user matching the given id