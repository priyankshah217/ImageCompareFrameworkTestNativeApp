Feature: Checking "Api Demo" app
  As a QA i want to native android app "Api Demo"

  Background: "Api Demo"
    Given I open "Api Demo" application in my device
    Then I take a screenshot of "Home Screen" screen

  Scenario: Browse "Api Demo" application
    When I click on "App"
    Then I take a screenshot of "App" screen
    When I click on "Activity"
    Then I take a screenshot of "Activity" screen
    When I click on "Hello World"
    Then I take a screenshot of "Hello World" screen
