Feature: User Authentication
  As a user
  I want to authenticate to the application
  So that I can access my account

  Scenario: Successful User Login
    Given I am on the login page
    When I enter valid username "testuser"
    And I enter valid password "Test@123"
    And I click the login button
    Then I should see the dashboard page
    And I should see a welcome message

  Scenario: Failed Login - Invalid Credentials
    Given I am on the login page
    When I enter invalid username "baduser"
    And I enter invalid password "wrong"
    And I click the login button
    Then I should see an error message
    And I should remain on the login page