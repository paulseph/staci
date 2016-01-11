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

}
