Feature: Login Function
  As a user I want to be able to login
  with my valid username and password

  @SmokeTest
  Scenario: Login with valid username and password
    Given Navigate to NonCommerce WebSite
    And Click on login
    And Enter email
    And Enter password
    When Click on Login Button
    Then User should login successfully