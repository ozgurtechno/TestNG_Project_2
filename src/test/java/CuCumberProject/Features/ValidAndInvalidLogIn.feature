Feature: User Login
  As a user, I want to be able to log in to the website using the data provider method


@SmokeTest
  Scenario Outline: Login with valid and invalid credentials
    Given I navigate to the login page of the website
    When I enter "<email>" and "<password>"
    Then I should "<expectedOutcome>" be logged in

    Examples:
      | email                 | password | expectedOutcome  |
      | omar1.tarek@gmail.com | omar0922 | not be logged in |
      | omar.tarek@gmail.com  | omar22   | not be logged in |
      | omar.tarek@gmail.com  | omar0922 | be logged in     |
