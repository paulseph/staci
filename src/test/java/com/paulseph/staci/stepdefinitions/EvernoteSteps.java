package com.paulseph.staci.stepdefinitions;

import com.paulseph.staci.pageobjectmodels.EvernotePage;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
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

    @And("^I logout$")
    public void iLogout() throws Throwable {
        this.evernotePage.logout();
    }

    @And("^I create a shortcut to the first note$")
    public void iCreateAShortcutToTheFirstNote() {
        this.evernotePage.createShortcutToFirstNote();
    }

    @Then("^a shortcut with title '(.*)' is displayed in the Shortcut list$")
    public void aShortcutWithTitleIsDisplayedInTheShortcutList(String title) {
        assertTrue(this.evernotePage.aShortcutWithTitleIsDisplayedInTheShortcutList(title));
        this.evernotePage.deleteAllNotes();
    }

    @When("^I create a note with title '(.*)' and a table with (\\d+) rows and (\\d+) columns$")
    public void iCreateANoteWithTitleAndATable(String title, int rows, int columns) {
        this.evernotePage.createANoteWithTitleAndATable(title, rows, columns);
    }

    @Then("^a note with title '(.*)' and a table with (\\d+) rows and (\\d+) columns is created$")
    public void aNoteWithTitleAndATableWithRowsAndColumnsIsCreated(String title, int rows, int columns) throws Throwable {
        assertTrue(this.evernotePage.aNoteWithTitleAndATableWithRowsAndColumnsIsCreated(title, rows, columns));

        this.evernotePage.deleteAllNotes();
    }

    @Then("^sorting works well in the Notes list$")
    public void sortingWorksWellInTheNotesList() {
        assertTrue (this.evernotePage.sortingByDateCreatedOldestFirstWorksWell());
        assertTrue (this.evernotePage.sortingByDateCreatedNewestFirstWorksWell());
        assertTrue (this.evernotePage.sortingByTitleAscendingWorksWell());
        assertTrue (this.evernotePage.sortingByTitleDescendingWorksWell());

        this.evernotePage.deleteAllNotes();
    }


    @And("^I wait for (\\d+) seconds?$")
    public void iWaitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
