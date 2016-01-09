@evernote
Feature: Evernote Tests

  @e1 @e1a
  Scenario: Evernote Login With Correct Credentials
    Given I open the Evernote login page
    When I login with username 'paulseph.farrugia.99@um.edu.mt' and password 'staci_3007'
    Then I login successfully

  @e1 @e1b
  Scenario: Evernote Login With No Credentials
    Given I open the Evernote login page
    When I login with username '' and password ''
    Then the required field login error message is shown

  @e1 @e1c
  Scenario: Evernote Login With Incorrect Credentials
    Given I open the Evernote login page
    When I login with username 'foobar' and password 'badpassword'
    Then the incorrect username or password login error message is shown

  @e2 @e2a
   Scenario: Create Note with Title and Body
    Given I login with valid credentials
    When I create a note with title 'Evernote 2 a' and body 'Test Body'
    Then a note with title 'Evernote 2 a' is displayed in the Notes list

  @e2 @e2b
  Scenario: Create note and check it exists after logout and login
    Given I login with valid credentials
    When I create a note with title 'Evernote 2 b' and body 'Test Body'
    And I logout
    And I login with valid credentials
    Then a note with title 'Evernote 2 b' is displayed in the Notes list

  @e2 @e2c
  Scenario: Create note and mark it as favourite
    Given I login with valid credentials
    When I create a note with title 'Evernote 2 c' and body 'Test Body'
    And I create a shortcut to the first note
    Then a shortcut with title 'Evernote 2 c' is displayed in the Shortcut list

  @e2 @e2d
  Scenario: Create note with a table
    Given I login with valid credentials
    When I create a note with title 'Evernote 2 d' and a table with 3 rows and 3 columns
    Then a note with title 'Evernote 2 d' and a table with 3 rows and 3 columns is created
