package com.paulseph.staci.stepdefinitions;

import com.paulseph.staci.pageobjectmodels.EvernotePage;
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
    public void iLoginWithValidCredentials() {
        this.evernotePage.loginWithValidCredentials();
    }

    @When("^I create a note with title '(.*)' and body '(.*)'$")
    public void iCreateANoteWithTitleAndBody(String title, String body) {
        this.evernotePage.createANoteWithTitleAndBody(title, body);
    }

    @Then("^a note with title '(.*)' is displayed in the Notes list$")
    public void aNoteWithTitleIsDisplayedInTheNotesList(String title) {
        assertTrue(this.evernotePage.aNoteWithTitleIsDisplayedInTheNotesList(title));
        this.evernotePage.deleteAllNotes();
    }

    @And("^I logout$")
    public void iLogout() {
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
    public void aNoteWithTitleAndATableWithRowsAndColumnsIsCreated(String title, int rows, int columns) {
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

    @When("^I search for '(.*)'$")
    public void iSearchFor(String searchString) {
        this.evernotePage.searchFor(searchString);
    }

    @Then("^a note with title '(.*)' and body '(.*)' is shown$")
    public void aNoteWithTitleAndBodyIsShown(String title, String body) {
        assertTrue(this.evernotePage.aNoteWithTitleAndBodyIsShown(title, body));

        this.evernotePage.deleteAllNotes();
    }

    @When("^I create a notebook with title '(.*)'$")
    public void iCreateANotebookWithTitle(String title) {
        this.evernotePage.createANotebookWithTitle(title);
    }

    @And("^I move the note with title '(.*)' to the notebook with title '(.*)'$")
    public void iMoveTheNoteWithTitleToTheNotebookWithTitle(String noteTitle, String notebookTitle) {
        this.evernotePage.moveTheNoteWithTitleToTheNotebookWithTitle(noteTitle, notebookTitle);
    }

    @Then("^the note with title '(.*)' and body '(.*)' exists in the notebook with title '(.*)'$")
    public void theNoteWithTitleAndBodyExistsInTheNotebookWithTitle(String noteTitle, String noteBody, String notebookTitle) {
        assertTrue(this.evernotePage.theNoteWithTitleAndBodyExistsInTheNotebookWithTitle(noteTitle, noteBody, notebookTitle));
    }

    @And("^the note with title '(.*)' and body '(.*)' does not exist in the notebook with title '(.*)'$")
    public void theNoteWithTitleAndBodyDoesNotExistInTheNotebookWithTitle(String noteTitle, String noteBody, String notebookTitle) {
        assertFalse(this.evernotePage.theNoteWithTitleAndBodyExistsInTheNotebookWithTitle(noteTitle, noteBody, notebookTitle));
    }

    @And("^I delete notebook with title '(.*)'$")
    public void iDeleteNotebookWithTitle(String notebookTitle) {
        this.evernotePage.deleteNotebookWithTitle(notebookTitle);
    }

    @And("^I delete all notes$")
    public void iDeleteAllNotes() {
        this.evernotePage.deleteAllNotes();
    }

    @And("^I navigate to the trash can$")
    public void iNavigateToTheTrashCan()  {
        this.evernotePage.navigateToTheTrashCan();
    }

    @Then("^the note with title '(.*)' and body '(.*)' is displayed$")
    public void theNoteWithTitleAndBodyIsDisplayed(String noteTitle, String noteBody) {
        assertTrue(this.evernotePage.theNoteWithTitleAndBodyIsDisplayed(noteTitle, noteBody));
    }

    @When("^I click the Empty Trash button and empty the trash$")
    public void iClickTheEmptyTrashButtonAndEmptyTheTrash() {
        this.evernotePage.clickTheEmptyTrashButtonAndEmptyTheTrash();
    }

    @Then("^there are (\\d+) notes displayed$")
    public void thereAreNotesDisplayed(int num) {
        assertTrue(this.evernotePage.getNoteList().size() == num);
    }

    @And("^I restore the note with title '(.*)'$")
    public void iRestoreTheNoteWithTitle(String noteTitle) {
        this.evernotePage.restoreTheNoteWithTitle(noteTitle);
    }

    @And("^I navigate to the notes list$")
    public void iNavigateToTheNotesList()  {
        this.evernotePage.navigateToTheNotesList();
    }

    @And("^I assign the note with title '(.*)' the tag '(.*)'$")
    public void iAssignTheNoteWithTitleTheTag(String noteTitle, String tag) {
        this.evernotePage.assignTheNoteWithTitleTheTag(noteTitle, tag);
    }

    @And("^I navigate to tag '(.*)'$")
    public void iNavigateToTag(String tag) {
        this.evernotePage.navigateToTag(tag);
    }

    @Then("^I delete tag '(.*)'$")
    public void iDeleteTag(String tag) {
        this.evernotePage.deleteTag(tag);
    }
}
