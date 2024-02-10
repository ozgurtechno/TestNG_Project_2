Feature: Register Function
  As a user I want to be able to register
  on NopCommerce website and create my account so that I can access personalized content

  @Smoke
  Scenario: Register on the Website of NopCommerce
    Given Navigate to NonCommerce WebSite
    And Click on register
    And Click on gender male
    And Click on first name
    And Click on Last name
    And Click on select date
    And Click on select month
    And Click on select year
    And Click on email
    And Click company name
    And Click on password
    And Click on confirm password
    When Click on Register button
    Then Success message should be displayed


