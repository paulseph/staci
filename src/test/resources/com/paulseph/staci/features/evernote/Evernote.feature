@evernote
Feature: Evernote Test

  Scenario: Evernote Login With Correct Credentials
    Given I open the Evernote login page
    And I login with username '' and password ''
    Then I am succesfully logged in
