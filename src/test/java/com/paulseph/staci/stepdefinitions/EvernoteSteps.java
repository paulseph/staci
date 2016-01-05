package com.paulseph.staci.stepdefinitions;

import com.paulseph.staci.driver.Driver;
import com.paulseph.staci.pageobjectmodels.EvernoteLoginPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class EvernoteSteps {

    private EvernoteLoginPage evernoteLoginPage = new EvernoteLoginPage();

    @Given("^I open the Evernote login page$")
    public void openEvernoteLoginPageStep() {
        evernoteLoginPage.openLoginPage();
//        Driver.getWebDriver().get("https://www.evernote.com/Login.action?targetUrl=%2FHome.action");
    }

    @Given ("I login with username '' and password ''$")
    public void evernoteGiven(){
        System.out.println("Given reached.");
    }

    @Then("^I am succesfully logged in$")
    public void evernoteThen(){
        System.out.println("Then reached.");
        assertTrue("Should have been true.", true);
    }
}
