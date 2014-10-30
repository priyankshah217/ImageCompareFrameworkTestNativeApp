Feature: Checking 'secure dialog' in API Demo Application

Background: API demo

Given I Open API demo Application in my device
Scenario: Secure Dialog

When I click on 'App'
Then 'Activity' text visible on 'Application' screen
When I click on 'Activity'
Then 'Secure Surface' text visible on 'Activity' screen
When I click on 'Secure Surface'
Then 'Secure Dialog' text visble on 'Secure Surface' screen
When I click on 'Secure Dialog'
Then 'Show Secure Dialog' Button is displayed