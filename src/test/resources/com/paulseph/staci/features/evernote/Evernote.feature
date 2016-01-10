@evernote
Feature: Evernote Tests

  @e1 @e1a
  Scenario: Login With correct credentials
    Given I open the Evernote login page
    When I login with username 'paulseph.farrugia.99@um.edu.mt' and password 'staci_3007'
    Then I login successfully

  @e1 @e1b
  Scenario: Login with no credentials
    Given I open the Evernote login page
    When I login with username '' and password ''
    Then the required field login error message is shown

  @e1 @e1c
  Scenario: Login with incorrect credentials
    Given I open the Evernote login page
    When I login with username 'foobar' and password 'badpassword'
    Then the incorrect username or password login error message is shown

  @e2 @e2a
   Scenario: Create a note with title and body
    Given I login with valid credentials
    When I create a note with title 'Evernote 2 a' and body 'Test Body'
    Then a note with title 'Evernote 2 a' is displayed in the Notes list

  @e2 @e2b
  Scenario: Create a note and check it exists after logout and login
    Given I login with valid credentials
    When I create a note with title 'Evernote 2 b' and body 'Test Body'
    And I logout
    And I login with valid credentials
    Then a note with title 'Evernote 2 b' is displayed in the Notes list

  @e2 @e2c
  Scenario: Create a note and mark it as favourite
    Given I login with valid credentials
    When I create a note with title 'Evernote 2 c' and body 'Test Body'
    And I create a shortcut to the first note
    Then a shortcut with title 'Evernote 2 c' is displayed in the Shortcut list

  @e2 @e2d
  Scenario: Create a note with a table
    Given I login with valid credentials
    When I create a note with title 'Evernote 2 d' and a table with 3 rows and 3 columns
    Then a note with title 'Evernote 2 d' and a table with 3 rows and 3 columns is created

  @e2 @e2e
  Scenario: Create 3 notes and ensure sorting works well
    Given I login with valid credentials
    When I create a note with title 'Evernote 2 e 1' and body 'Test Body 1'
    And I wait for 1 second
    And I create a note with title 'Evernote 2 e 3' and body 'Test Body 3'
    And I wait for 1 second
    And I create a note with title 'Evernote 2 e 2' and body 'Test Body 2'
    And I wait for 1 second
    Then sorting works well in the Notes list

  @e3 @e3a
  Scenario: Create 3 notes and search for 1 note with the note title
    Given I login with valid credentials
    When I create a note with title 'Evernote 3 a 1' and body 'Test Body 1'
    And I create a note with title 'Evernote 3 a 2' and body 'Test Body 2'
    And I create a note with title 'Evernote 3 a 3' and body 'Test Body 3'
    When I search for 'Evernote 3 a 2'
    Then a note with title 'Evernote 3 a 2' and body 'Test Body 2' is shown

  @e3 @e3b
  Scenario: Create 3 notes and search for 1 note with part of the body text
    Given I login with valid credentials
    When I create a note with title 'Evernote 3 b 1' and body 'Test Body 1'
    And I create a note with title 'Evernote 3 b 2' and body 'Test Body 2'
    And I create a note with title 'Evernote 3 b 3' and body 'Test Body 3'
    When I search for 'Body 2'
    Then a note with title 'Evernote 3 b 2' and body 'Test Body 2' is shown

  @e4
  Scenario: Create a notebook and move note
    Given I login with valid credentials
    When I create a notebook with title 'Notebook Testing'
    And I create a note with title 'Evernote 4' and body 'Test Body'
    And I move the note with title 'Evernote 4' to the notebook with title 'Notebook Testing'
    Then the note with title 'Evernote 4' and body 'Test Body' exists in the notebook with title 'Notebook Testing'
    And the note with title 'Evernote 4' and body 'Test Body' does not exist in the notebook with title 'First Notebook'
    And I delete notebook with title 'Notebook Testing'

  @e5 @e5a
  Scenario: Create 3 notes, delete them and empty the trash can
    Given I login with valid credentials
    When I create a note with title 'Evernote 5 a 1' and body 'Test Body 1'
    And I create a note with title 'Evernote 5 a 2' and body 'Test Body 2'
    And I create a note with title 'Evernote 5 a 3' and body 'Test Body 3'
    And I delete all notes
    And I navigate to the trash can
    Then the note with title 'Evernote 5 a 1' and body 'Test Body 1' is displayed
    And the note with title 'Evernote 5 a 2' and body 'Test Body 2' is displayed
    And the note with title 'Evernote 5 a 3' and body 'Test Body 3' is displayed
    When I click the Empty Trash button and empty the trash
    Then there are no notes displayed

