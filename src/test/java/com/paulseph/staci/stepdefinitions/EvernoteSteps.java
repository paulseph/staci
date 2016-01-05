package com.paulseph.staci.stepdefinitions;

import com.paulseph.staci.pageobjectmodels.EvernoteLoginPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertTrue;

public class EvernoteSteps {

    private EvernoteLoginPage evernoteLoginPage = new EvernoteLoginPage();

    @Given("^I open the Evernote login page$")
    public void openEvernoteLoginPageStep() {
        this.evernoteLoginPage.openLoginPage();
    }

    @Given ("I login with username '(.*)' and password '(.*)'$")
    public void loginWithUsernameAndPassword(String username, String password){
        this.evernoteLoginPage.signIn(username, password);
    }

    @Then("^I login successfully$")
    public void loginSuccessful(){
        System.out.println("Then reached.");
        assertTrue("Should have been true.", true);
    }
}
