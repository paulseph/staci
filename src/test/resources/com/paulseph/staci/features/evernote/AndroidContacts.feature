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


