package com.paulseph.staci.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class EvernoteSteps {
    @Given ("^I test$")
    public void evernoteGiven(){
        System.out.println("Given reached.");
    }

    @Then("^I get result$")
    public void evernoteThen(){
        System.out.println("Then reached.");
        assertTrue("Should have been true.", false);
    }
}
