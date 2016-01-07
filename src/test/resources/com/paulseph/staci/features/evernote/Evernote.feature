@evernote
Feature: Evernote Test

  @e1a
  Scenario: Evernote Login With Correct Credentials
    Given I open the Evernote login page
    And I login with username 'paulseph.farrugia.99@um.edu.mt' and password 'staci_3007'
    Then I login successfully

  @e1b
  Scenario: Evernote Login With No Credentials
    Given I open the Evernote login page
    And I login with username '' and password ''
    Then the required field login error message is shown

  @e1c
  Scenario: Evernote Login With Incorrect Credentials
    Given I open the Evernote login page
    And I login with username 'foobar' and password 'badpassword'
    Then the incorrect username or password login error message is shown

  @e2a
   Scenario: Create Note with Title and Body
    Given I login with valid credentials
    And I create a note with title 'Evernote 2 a' and body 'Test Body'
    Then a note with title 'Evernote 2 a' is displayed in the Notes list

