package com.paulseph.staci.stepdefinitions;

import com.paulseph.staci.pageobjectmodels.AndroidContactsPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AndroidContactsSteps {

    private AndroidContactsPage androidContactsPage = new AndroidContactsPage();

    @Given("^I click on Create A New Contact button$")
    public void iClickOnCreateANewContactButton() {
        this.androidContactsPage.clickOnCreateANewContactButton();
    }

    @When("^I input the name '(.*)'$")
    public void iInputTheName(String name) {
        this.androidContactsPage.inputTheName(name);
    }

    @And("^I input the mobile number (\\d+)$")
    public void iInputTheMobileNumber(String mobileNumber) {
        this.androidContactsPage.inputTheMobileNumber(mobileNumber);
    }

    @And("^I input the home number (\\d+)$")
    public void iInputTheHomeNumber(String homeNumber) {
        this.androidContactsPage.inputTheHomeNumber(homeNumber);
    }

    @And("^I input the email address '(.*)'$")
    public void iInputTheEmailAddress(String emailAddress) {
        this.androidContactsPage.inputTheEmailAddress(emailAddress);
    }

    @And("^I navigate back$")
    public void iNavigateBack() {
        this.androidContactsPage.navigateBack();
    }

    @Then("^the contact page shows the name '(.*)'$")
    public void theContactPagesShowTheName(String name) {
        assertTrue(this.androidContactsPage.contactPageShowsTheName(name));
    }

    @And("^the contact page shows the mobile number (\\d+)$")
    public void theContactPageShowsTheMobileNumber(String mobileNumber) {
        assertTrue(this.androidContactsPage.contactPageShowsTheMobileNumber(mobileNumber));
    }

    @And("^the contact page shows the home number (\\d+)$")
    public void theContactPageShowsTheHomeNumber(String homeNumber) {
        assertTrue(this.androidContactsPage.contactPageShowsTheHomeNumber(homeNumber));
    }

    @And("^the contact page shows the email address '(.*)'$")
    public void theContactPageShowsTheEmailAddress(String emailAddress) {
        assertTrue(this.androidContactsPage.contactPageShowsTheEmailAddress(emailAddress));
    }

    @Then("^a contact with name '(.*)' is shown in contacts list$")
    public void aContactWithNameIsShownInContactsList(String name) {
        assertTrue(this.androidContactsPage.aContactWithNameIsShownInContactsList(name));
    }

    @And("^I delete all contacts$")
    public void iDeleteAllContacts() {
        this.androidContactsPage.deleteAllContacts();
    }

    @When("^I open the contact with name '(.*)'$")
    public void iOpenTheContactWithName(String name) {
        this.androidContactsPage.openTheContactWithName(name);
    }

    @And("^I click the edit contact button$")
    public void iClickTheEditContactButton() {
        this.androidContactsPage.clickTheEditContactButton();
    }

    @Given("^I click on Favourites button$")
    public void iClickOnFavouritesButton() {
        this.androidContactsPage.clickOnFavouritesButton();
    }


    @Then("^there are no contacts shown in the favourites list$")
    public void thereAreNoContactsShownInTheFavouritesList() {
        assertTrue(this.androidContactsPage.thereAreNoContactsShownInTheFavouritesList());
    }

    @And("^I mark the contact as favourite$")
    public void iMarkTheContactAsFavourite() {
        this.androidContactsPage.iMarkTheContactAsFavourite();
    }

}
