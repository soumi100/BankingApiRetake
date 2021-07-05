Feature: Users

  Background:
    Given User is Logged in

  Scenario: Create User
    When Employee is creating a new User
    Then I get http status 201

  Scenario: Get All Users
    When I retrieve all users
    Then I get http status 200

  Scenario: Get A Users
    When I retrieve a users
    Then I get http status 200