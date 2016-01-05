package com.paulseph.staci.stepdefinitions;

import com.paulseph.staci.pageobjectmodels.EvernoteLoginPage;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EvernoteSteps {

    private EvernoteLoginPage evernoteLoginPage = new EvernoteLoginPage();

    @Given("^I open the Evernote login page$")
    public void openEvernoteLoginPageStep() {
        // Open login page and ensure error messages are not shown.
        this.evernoteLoginPage.openLoginPage();
        assertFalse(this.evernoteLoginPage.requiredFieldLoginErrorMessageIsShown());
        assertFalse(this.evernoteLoginPage.incorrectUsernameOrPasswordLoginErrorMessageIsShown());
    }

    @Given ("I login with username '(.*)' and password '(.*)'$")
    public void loginWithUsernameAndPassword(String username, String password){
        this.evernoteLoginPage.login(username, password);
    }

    @Then("^I login successfully$")
    public void loginSuccessful(){
        assertTrue(this.evernoteLoginPage.userIsLoggedIn());
    }

    @Then("^the required field login error message is shown$")
    public void theRequiredFieldLoginErrorMessageIsShown(){
        assertTrue(this.evernoteLoginPage.requiredFieldLoginErrorMessageIsShown());
    }

    @Then("^the incorrect username or password login error message is shown$")
    public void theIncorrectUsernameOrPasswordLoginErrorMessageIsShown()  {
        assertTrue(this.evernoteLoginPage.incorrectUsernameOrPasswordLoginErrorMessageIsShown());
    }
}
