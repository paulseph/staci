@androidContacts
Feature: Android Contacts Tests

  @ac1
  Scenario: Create new contact
    Given I click on Create A New Contact button
    When I input the name 'Android Contact'
    And I input the mobile number 35679123456
    And I input the home number 35621123456
    And I input the email address 'contact@android.com'
    And I navigate back
    Then the contact page shows the name 'Android Contact'
    And the contact page shows the mobile number 35679123456
    And the contact page shows the home number 35621123456
    And the contact page shows the email address 'contact@android.com'
    When I navigate back
    Then a contact with name 'Android Contact' is shown in contacts list

  @ac2
  Scenario: Search for contact
    Given I click on Create A New Contact button
    When I input the name 'Search Contact'
    And I input the mobile number 35679111111
    And I navigate back
    And I navigate back
    Then a contact with name 'Search Contact' is shown in contacts list
    When I open the contact with name 'Search Contact'
    And I click the edit contact button
    And I input the name 'Changed Contact'
    And I input the mobile number 35679999999
    And I navigate back
    Then the contact page shows the name 'Changed Contact'
    And the contact page shows the mobile number 35679999999



