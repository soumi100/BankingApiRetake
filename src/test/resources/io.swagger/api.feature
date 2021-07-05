Feature: API test

Scenario: Retrieve all guitars is status OK
   When I retrieve all users
   Then I get http status 200