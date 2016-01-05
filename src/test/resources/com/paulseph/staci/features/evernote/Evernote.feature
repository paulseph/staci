@evernote
Feature: Evernote Test

  @q1a
  Scenario: Evernote Login With Correct Credentials
    Given I open the Evernote login page
    And I login with username 'paulseph.farrugia.99@um.edu.mt' and password 'staci_3007'
    Then I login successfully

  @q1b
  Scenario: Evernote Login With No Credentials
    Given I open the Evernote login page
    And I login with username '' and password ''
    Then the required field login error message is shown

  @q1c
  Scenario: Evernote Login With Incorrect Credentials
    Given I open the Evernote login page
    And I login with username 'baduser' and password 'badpassword'
    Then I login successfully