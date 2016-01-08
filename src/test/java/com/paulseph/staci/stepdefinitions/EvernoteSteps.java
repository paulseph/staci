package com.paulseph.staci.stepdefinitions;

import com.paulseph.staci.pageobjectmodels.EvernotePage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EvernoteSteps {

    private EvernotePage evernotePage = new EvernotePage();

    @Given("^I open the Evernote login page$")
    public void openEvernoteLoginPageStep() {
        // Open login page and ensure error messages are not shown.
        this.evernotePage.openLoginPage();
        assertFalse(this.evernotePage.requiredFieldLoginErrorMessageIsShown());
        assertFalse(this.evernotePage.incorrectUsernameOrPasswordLoginErrorMessageIsShown());
    }

    @When("I login with username '(.*)' and password '(.*)'$")
    public void loginWithUsernameAndPassword(String username, String password){
        this.evernotePage.login(username, password);
    }

    @Then("^I login successfully$")
    public void loginSuccessful(){
        assertTrue(this.evernotePage.userIsLoggedIn());
    }

    @Then("^the required field login error message is shown$")
    public void theRequiredFieldLoginErrorMessageIsShown(){
        assertTrue(this.evernotePage.requiredFieldLoginErrorMessageIsShown());
    }

    @Then("^the incorrect username or password login error message is shown$")
    public void theIncorrectUsernameOrPasswordLoginErrorMessageIsShown()  {
        assertTrue(this.evernotePage.incorrectUsernameOrPasswordLoginErrorMessageIsShown());
    }

    @Given("^I login with valid credentials$")
    public void iLoginWithValidCredentials() throws Throwable {
        this.evernotePage.loginWithValidCredentials();
    }

    @When("^I create a note with title '(.*)' and body '(.*)'$")
    public void iCreateANoteWithTitleAndBody(String title, String body) throws Throwable {
        this.evernotePage.createANoteWithTitleAndBody(title, body);
    }

    @Then("^a note with title '(.*)' is displayed in the Notes list$")
    public void aNoteWithTitleIsDisplayedInTheNotesList(String title) {
        assertTrue(this.evernotePage.aNoteWithTitleIsDisplayedInTheNotesList(title));
        this.evernotePage.deleteAllNotes();
    }

}
