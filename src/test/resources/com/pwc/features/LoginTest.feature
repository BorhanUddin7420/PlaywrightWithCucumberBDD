Feature: Login page feature

  Scenario Outline: User successfully login with valid username and password
    Given customer is on login page
    And Verify login page title should be "Your store. Login"
    When customer input "<email>" and "<password>" in login page
    And customer click login button in login page
    Then Verify customer login successfully
    Examples:
      | email               | password     |
      | admin@yourStore.com | invalidPassword |
      | borhan@bs-23.net    | 12345678     |