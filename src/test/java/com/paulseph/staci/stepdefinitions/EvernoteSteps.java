package com.paulseph.staci.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EvernoteSteps {
    @Given ("^I test$")
    public void evernoteGiven(){
        System.out.println("Given reached.");
    }

    @Then("^I get result$")
    public void evernoteThen(){
        System.out.println("Then reached.");
    }
}
