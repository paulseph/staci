package com.paulseph.staci.pageobjectmodels;

import com.paulseph.staci.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class EvernotePage {
    private static String EVERNOTE_LOGIN_URL = "https://www.evernote.com/Login.action?targetUrl=%2FHome.action";

    private static String REQUIRED_FIELD_ERROR_MESSAGE = "This is a required field.";
    private static String INCORRECT_USERNAME_OR_PASSWORD_ERROR_MESSAGE = "Incorrect username and/or password.";

    private static String VALID_USERNAME = "paulseph.farrugia.99@um.edu.mt";
    private static String VALID_PASSWORD = "staci_3007";

    private WebDriver driver = Driver.getWebDriver();

    public void openLoginPage(){
        this.driver.get(EvernotePage.EVERNOTE_LOGIN_URL);
    }

    public void inputUsername(String username) {
        if ((username != null) && (username.length() > 0)) {
            this.driver.findElement(By.id("username")).sendKeys(username);
        }
    }

    public void inputPassword(String password) {
        if ((password != null) && (password.length() > 0)) {
            this.driver.findElement(By.id("password")).sendKeys(password);
        }
    }

    public void pressSignInButton() {
        this.driver.findElement(By.id("login")).click();
    }

    public void login(String username, String password)  {
        this.inputUsername(username);
        this.inputPassword(password);
        this.pressSignInButton();
    }

    // Checks if the user is logged in by searching for the user name tag
    public boolean userIsLoggedIn() {
        return this.driver.findElements(By.cssSelector(".User-nameText")).size() > 0;
    }

    // Check if specific error message is displayed in text.
    public boolean loginErrorMessageIsShown(String errorMessage){
        return this.driver.getPageSource().contains(errorMessage);
    }

    public boolean requiredFieldLoginErrorMessageIsShown(){
        return this.loginErrorMessageIsShown(EvernotePage.REQUIRED_FIELD_ERROR_MESSAGE);
    }

    public boolean incorrectUsernameOrPasswordLoginErrorMessageIsShown(){
        return this.loginErrorMessageIsShown(EvernotePage.INCORRECT_USERNAME_OR_PASSWORD_ERROR_MESSAGE);
    }

    // Open login page and login with valid credentials.
    public void loginWithValidCredentials(){
        this.openLoginPage();
        this.login(EvernotePage.VALID_USERNAME, EvernotePage.VALID_PASSWORD);
        this.waitForPageToLoad(1);
    }

    private void clickWebElementWithJavascript(WebElement webElement){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) this.driver;
        javascriptExecutor.executeScript("arguments[0].click();", webElement);
    }

    private void waitForWebElementToBeClickable(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(this.driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    private void waitForWebElementToBeClickableAndClick(WebElement webElement) {
        this.waitForWebElementToBeClickable(webElement);
        webElement.click();
    }

    private WebElement getNewNoteButtonWebElement() {
        return this.driver.findElement(By.cssSelector("#gwt-debug-Sidebar-newNoteButton"));
    }

    private void clickNewNoteButton() {
        WebElement newNoteButtonWebElement = this.getNewNoteButtonWebElement();
        this.waitForWebElementToBeClickableAndClick(newNoteButtonWebElement);
    }


    private void clickNotesButton() {
        WebElement notesButtonWebElement = this.driver.findElement(By.cssSelector("#gwt-debug-Sidebar-notesButton-container > div:nth-child(1) > div:nth-child(1) > img:nth-child(2)"));
        this.clickWebElementWithJavascript(notesButtonWebElement);
        this.waitForPageToLoad(1);
    }


    private WebElement getNotesHeaderTitleWebElement() {
        return this.driver.findElement(By.cssSelector("#gwt-debug-NotesHeader-title"));
    }


    private void writeNoteTitle(String title) {
        WebElement noteTitleWebElement = this.driver.findElement(By.cssSelector("#gwt-debug-NoteTitleView-textBox"));
        noteTitleWebElement.sendKeys(title);
    }

    private void writeNoteBody(String body) {
        this.switchToBodyFrame();
        WebElement we = this.driver.switchTo().activeElement();
        we.sendKeys(body);
        this.switchToDefaultContent();
    }

    private void clickNoteDoneButton() {
        WebElement noteDoneButtonWebElement = this.driver.findElement(By.xpath("//button[text()='Done']"));
        this.clickWebElementWithJavascript(noteDoneButtonWebElement);
    }

    void waitForPageToLoad(int seconds) {
        // A primitive way to ensure that all dynamic content has loaded by simply adding a wait.
        // This should ideally be replaced with better logic.
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void clickConfirmationDialogConfirmButton() {
        WebElement confirmButtonWebElement = this.driver.findElement(By.cssSelector("#gwt-debug-ConfirmationDialog-confirm"));
        confirmButtonWebElement.click();
    }

    public void deleteAllNotes() {
        this.clickNotesButton();
        this.waitForPageToLoad(1);
        while (this.driver.findElements(By.cssSelector(".qa-deleteButton")).size() > 0) {
            WebElement noteDeleteButtonWebElement = this.driver.findElement(By.cssSelector(".qa-deleteButton"));

            this.clickWebElementWithActions(noteDeleteButtonWebElement);

            clickConfirmationDialogConfirmButton();

            this.clickNotesButton();
            this.waitForPageToLoad(1);
        }
    }


    private void clickWebElementWithActions(WebElement noteDeleteButtonWebElement) {
        Actions action = new Actions(this.driver);
        action.click(noteDeleteButtonWebElement).build().perform();
    }


    public void createANoteWithTitleAndBody(String title, String body) {
        this.clickNotesButton();
        this.clickNewNoteButton();
        this.writeNoteTitle(title);
        this.writeNoteBody(body);
        this.clickNoteDoneButton();
    }

    public boolean aNoteWithTitleIsDisplayedInTheNotesList(String title) {
        this.clickNotesButton();
        return this.driver.findElements(
                By.xpath(
                        "//div[contains(@class, 'focus-NotesView-Note-noteTitle') and text() = '"
                                + title
                                + "']")).size() > 0;
    }

    public void logout() {
        this.waitForPageToLoad(4);

        WebElement accountButtonWebElement = this.driver.findElement(By.cssSelector(".GOSDSN-CJP"));
        this.waitForWebElementToBeClickableAndClick(accountButtonWebElement);

        WebElement logoutButtonWebElement = this.driver.findElement(By.cssSelector("#gwt-debug-AccountMenu-logout > div:nth-child(2) > div:nth-child(2)"));
        this.clickWebElementWithActions(logoutButtonWebElement);

    }

    // Creates a short cut to the first note displayed
    public void createShortcutToFirstNote() {
        this.clickNotesButton();
        WebElement shortcutButtonWebElement = this.driver.findElement(By.cssSelector(".qa-shortcutButton"));
        this.clickWebElementWithActions(shortcutButtonWebElement);
    }

    private void clickShorcutsButton() {
        WebElement shortcutsButtonWebElement = this.driver.findElement(By.cssSelector("#gwt-debug-Sidebar-shortcutsButton-container > div:nth-child(1) > div:nth-child(1) > img:nth-child(2)"));
        this.clickWebElementWithJavascript(shortcutsButtonWebElement);
    }

    public boolean aShortcutWithTitleIsDisplayedInTheShortcutList(String title) {
        this.clickShorcutsButton();
        return this.driver.findElements(
                By.xpath(
                        "//div[contains(@class, 'qa-name') and text() = '"
                                + title
                                + "']")).size() > 0;
    }

    private void writeNoteTable(int rows, int columns) {
        this.switchToBodyFrame();
        WebElement bodyWebElement = this.driver.switchTo().activeElement();
        bodyWebElement.click();
        this.switchToDefaultContent();

        WebElement tableButtonWebElement = this.driver.findElement(By.cssSelector("#gwt-debug-FormattingBar-tableButton"));
        tableButtonWebElement.click();

        WebElement tableChoiceWebElement = this.driver.findElement(
                By.cssSelector("div.GOSDSN-CDN:nth-child("
                    + rows
                    + ") > div:nth-child("
                    + columns
                    + ")"));
        tableChoiceWebElement.click();
    }

    public void createANoteWithTitleAndATable(String title, int rows, int columns) {
        this.clickNotesButton();
        this.clickNewNoteButton();
        this.writeNoteTitle(title);
        this.writeNoteTable(rows, columns);
//        this.writeNoteBody(rows + "x" + columns); // Could be used to cross check bottom right corner
        this.clickNoteDoneButton();
    }

    public void clickOnNoteWithTitle(String title) {
        this.clickNotesButton();
        WebElement noteTitleWebElement = this.driver.findElement(
                By.xpath(
                        "//div[contains(@class, 'focus-NotesView-Note-noteTitle') and text() = '"
                                + title
                                + "']"));

        this.clickWebElementWithActions(noteTitleWebElement);
    }

    // Ensure note with given title exists and has a table with the indicated rows and columns
    public boolean aNoteWithTitleAndATableWithRowsAndColumnsIsCreated(String title, int rows, int columns) {
        boolean aNoteWithTitleAndATableWithRowsAndColumnsIsCreated = false;

        if (this.aNoteWithTitleIsDisplayedInTheNotesList(title)) {
            this.clickOnNoteWithTitle(title);

            aNoteWithTitleAndATableWithRowsAndColumnsIsCreated = this.driver.findElements(
                    By.cssSelector(
                            "table:nth-child(1) > tbody:nth-child(1) > tr:nth-child("
                                    + rows
                                    + ") > td:nth-child("
                                    + columns
                                    + ")")).size() > 0;
        }

        return aNoteWithTitleAndATableWithRowsAndColumnsIsCreated;
    }

    private void switchToDefaultContent() {
        this.driver.switchTo().defaultContent();
    }

    private void switchToBodyFrame() {
        this.driver.switchTo().frame("entinymce_170_ifr");
    }


    private void clickOptionsButton() {
        WebElement optionsButtonWebElement = this.driver.findElement(By.cssSelector(".focus-NotesView-Subheader-OptionsButton"));
        this.waitForWebElementToBeClickableAndClick(optionsButtonWebElement);
    }

    private void clickSortByDateCreatedOldestFirstOption() {
        WebElement sortByDateCreatedOldestFirstOptionWebElement = this.driver.findElement(By.cssSelector("div.SelectorOption:nth-child(1)"));
        this.waitForWebElementToBeClickableAndClick(sortByDateCreatedOldestFirstOptionWebElement);
        this.waitForPageToLoad(1);
    }

    private void clickSortByDateCreatedNewestFirstOption() {
        WebElement sortByDateCreatedNewestFirstOptionWebElement = this.driver.findElement(By.cssSelector("div.SelectorOption:nth-child(2)"));
        this.waitForWebElementToBeClickableAndClick(sortByDateCreatedNewestFirstOptionWebElement);
        this.waitForPageToLoad(1);
    }

    private void clickSortByTitleAscendingOption() {
        WebElement sortByTitleAscendingOptionWebElement = this.driver.findElement(By.cssSelector("div.SelectorOption:nth-child(5)"));
        this.waitForWebElementToBeClickableAndClick(sortByTitleAscendingOptionWebElement);
        this.waitForPageToLoad(1);
    }

    private void clickSortByTitleDescendingOption() {
        WebElement sortByTitleDescendingOptionWebElement = this.driver.findElement(By.cssSelector("div.SelectorOption:nth-child(6)"));
        this.waitForWebElementToBeClickableAndClick(sortByTitleDescendingOptionWebElement);
        this.waitForPageToLoad(1);
    }

    private List<Comparable> getNoteAgeInSecondsList() {
        List<Comparable> noteAgeInSecondsList = new ArrayList<>();

        List<WebElement> noteDateWebElementList = this.driver.findElements(By.cssSelector(".qa-date"));

        for (WebElement noteDateWebElement : noteDateWebElementList) {
            String noteDateText = noteDateWebElement.getText().trim();

            int noteAgeInSeconds = 0;

            if ("MOMENTS AGO".equals(noteDateText)) {
                noteAgeInSeconds = 0;
            } else if (noteDateText.contains("SECOND AGO") || noteDateText.contains("SECONDS AGO")) {
                noteAgeInSeconds = Integer.valueOf(noteDateText.substring(0, noteDateText.indexOf(' ')));
            } else if (noteDateText.contains("MINUTE AGO") || noteDateText.contains("MINUTES AGO")) {
                noteAgeInSeconds = Integer.valueOf(noteDateText.substring(0, noteDateText.indexOf(' '))) * 60;
            } else {
                throw new RuntimeException("Note date format longer than 1 hour not supported by test.");
            }

            noteAgeInSecondsList.add(new Integer(noteAgeInSeconds));
        }

        return noteAgeInSecondsList;
    }

    private void clickNoteInfoButton() {
        WebElement noteInfoButtonWebElement = this.driver.findElement(By.cssSelector("#gwt-debug-NoteAttributes-infoButton"));
        this.clickWebElementWithActions(noteInfoButtonWebElement);
    }

    private void clickNoteInfoViewCancelButton() {
        WebElement noteInfoViewCancelButtonWebElement = this.driver.findElement(By.xpath("//span[text()='Cancel']"));
        this.clickWebElementWithActions(noteInfoViewCancelButtonWebElement);
    }

    private String getNoteInfoViewCreatedDate() {
        WebElement noteInfoViewCreatedDateWebElement = this.driver.findElement(
                By.cssSelector("#gwt-debug-NoteInfoDialog-createdContainer.GOSDSN-CBEC span.GOSDSN-CCEC.GOSDSN-CBD"));
        return noteInfoViewCreatedDateWebElement.getText();
    }


    private List<Comparable> getNoteTitleList() {
        List<Comparable> noteTitleList = new ArrayList<>();

        List<WebElement> noteTitleWebElementList = this.driver.findElements(By.cssSelector(".qa-title"));

        for (WebElement noteTitleWebElement : noteTitleWebElementList) {
            String noteTitleText = noteTitleWebElement.getText().trim();

            noteTitleList.add(noteTitleText);
        }

        return noteTitleList;
    }

    // Returns the list of notes displayed
    public List<EvernoteNote> getNoteList() {
        List<EvernoteNote> noteList = new ArrayList<>();

        List<WebElement> noteWebElementList = this.driver.findElements(By.cssSelector(".focus-NotesView-Note-snippetContent"));

        for (WebElement noteWebElement : noteWebElementList) {

            String noteTitle = noteWebElement.findElement(By.cssSelector(".qa-title")).getText();
            String noteBody = noteWebElement.findElement(By.cssSelector(".qa-snippet")).getText();

            noteList.add(new EvernoteNote(noteTitle, noteBody));
        }

        return noteList;
    }



    // Returns true if list provided is sorted ascending
    private static boolean isListSortedAscending(List<Comparable> list) {
        if(list == null || list.isEmpty())
            return true;

        if(list.size() == 1)
            return true;

        for(int i = 1; i < list.size(); i++) {
            if(list.get(i).compareTo(list.get(i-1)) < 0 )
                return false;
        }

        return true;
    }

    // Returns true if list provided is sorted descending
    private static boolean isListSortedDescending(List<Comparable> list) {
        if(list == null || list.isEmpty())
            return true;

        if(list.size() == 1)
            return true;

        for(int i = 1; i < list.size(); i++) {
            if(list.get(i-1).compareTo(list.get(i)) < 0 )
                return false;
        }

        return true;
    }

    public boolean sortingByDateCreatedOldestFirstWorksWell() {
        this.clickNotesButton();
        this.clickOptionsButton();
        this.clickSortByDateCreatedOldestFirstOption();
        List<Comparable> noteAgeInSecondsList = this.getNoteAgeInSecondsList();
        return EvernotePage.isListSortedDescending(noteAgeInSecondsList);
    }

    public boolean sortingByDateCreatedNewestFirstWorksWell() {
        this.clickNotesButton();
        this.clickOptionsButton();
        this.clickSortByDateCreatedNewestFirstOption();
        List<Comparable> noteAgeInSecondsList = this.getNoteAgeInSecondsList();
        return EvernotePage.isListSortedAscending(noteAgeInSecondsList);
    }


    public boolean sortingByTitleAscendingWorksWell() {
        this.clickNotesButton();
        this.clickOptionsButton();
        this.clickSortByTitleAscendingOption();
        List<Comparable> noteTitleList = this.getNoteTitleList();
        return EvernotePage.isListSortedAscending(noteTitleList);
    }

    public boolean sortingByTitleDescendingWorksWell() {
        this.clickNotesButton();
        this.clickOptionsButton();
        this.clickSortByTitleDescendingOption();
        List<Comparable> noteTitleList = this.getNoteTitleList();
        return EvernotePage.isListSortedDescending(noteTitleList);
    }

    private void clickSearchButton() {
        WebElement searchButtonWebElement = this.driver.findElement(By.cssSelector("#gwt-debug-Sidebar-searchButton-container > div:nth-child(1) > div:nth-child(1) > img:nth-child(2)"));
        this.clickWebElementWithJavascript(searchButtonWebElement);
    }

    private void inputSearchString(String searchString) {
        WebElement searchBoxWebElement = this.driver.findElement(By.cssSelector("#gwt-debug-searchViewSearchBox"));
        searchBoxWebElement.sendKeys(searchString + "\n");
        this.waitForPageToLoad(2);
    }


    public void searchFor(String searchString) {
        this.clickSearchButton();

        this.inputSearchString(searchString);
    }

    public boolean aNoteWithTitleAndBodyIsShown(String title, String body) {
        List<EvernoteNote> evernoteNoteList = this.getNoteList();

        EvernoteNote evernoteNote = new EvernoteNote(title, body);

        return evernoteNoteList.contains(evernoteNote);
    }

    private void clickNotebooksButton() {
        WebElement notebooksButtonWebElement = this.driver.findElement(By.cssSelector("#gwt-debug-Sidebar-notebooksButton-container > div:nth-child(1) > div:nth-child(1) > img:nth-child(2)"));
        this.clickWebElementWithJavascript(notebooksButtonWebElement);
        this.waitForPageToLoad(3);
    }

    private void clickCreateNotebookButton() {
        WebElement createNotebookButtonWebElement = this.driver.findElement(By.cssSelector("#gwt-debug-NotebooksDrawer-createNotebookButton"));
        this.clickWebElementWithJavascript(createNotebookButtonWebElement);
    }

    private void clickCreateNotebookDialogConfirmButton() {
        WebElement createNotebookDialogConfirmButtonWebElement = this.driver.findElement(By.cssSelector("#gwt-debug-CreateNotebookDialog-confirm"));
        createNotebookDialogConfirmButtonWebElement.click();
    }

    public void createANotebookWithTitle(String title) {
        this.clickNotebooksButton();
        this.clickCreateNotebookButton();
        this.driver.switchTo().activeElement().sendKeys(title);
        this.clickCreateNotebookDialogConfirmButton();
        this.waitForPageToLoad(3);
    }

    private WebElement getNotebookSelectMenuWebElement() {
        return this.driver.findElement(By.cssSelector("#gwt-debug-NotebookSelectMenu-notebookName"));
    }

    private void clickNotebookSelectMenu() {
        WebElement notebookSelectMenuWebElement = this.getNotebookSelectMenuWebElement();
        this.clickWebElementWithJavascript(notebookSelectMenuWebElement);
    }

    public void moveTheNoteWithTitleToTheNotebookWithTitle(String noteTitle, String notebookTitle) {
        this.clickNotesButton();

        List<WebElement> noteTitleWebElementList = this.driver.findElements(
                By.xpath(
                        "//div[contains(@class, 'focus-NotesView-Note-noteTitle') and text() = '"
                                + noteTitle
                                + "']"));

        for (WebElement noteTitleWebElement : noteTitleWebElementList) {
            this.clickWebElementWithJavascript(noteTitleWebElement);

            this.clickNotebookSelectMenu();

            WebElement notebookChoiceWebElement = this.getNotebookSelectMenuWebElement().findElement(
                    By.xpath("//*[text() = '" + notebookTitle + "']"));

            this.clickWebElementWithJavascript(notebookChoiceWebElement);

            this.waitForPageToLoad(2);
        }
    }

    private void clickNotebookWithTitle(String notebookTitle) {
        WebElement notebookWithTitleWebElement
                = this.driver.findElement( By.xpath("//*/div[contains(@class,'qa-notebookWidget')]/div/div[contains(@class, 'qa-name') and text() = '" + notebookTitle + "']/parent::*"));

        this.clickWebElementWithJavascript(notebookWithTitleWebElement);

        this.waitForPageToLoad(1);
    }

    private void openNotebookWithTitle(String notebookTitle) {
        this.clickNotebooksButton();
        this.clickNotebookWithTitle(notebookTitle);
    }

    public boolean theNoteWithTitleAndBodyExistsInTheNotebookWithTitle(String noteTitle, String noteBody, String notebookTitle) {
        this.openNotebookWithTitle(notebookTitle);

//        this.waitForPageToLoad(10);

        List<EvernoteNote> evernoteNoteList = this.getNoteList();

        return evernoteNoteList.contains(new EvernoteNote(noteTitle, noteBody));
    }


    public void deleteNotebookWithTitle(String notebookTitle) {
        this.clickNotebooksButton();

        // Select delete button corresponding to notebook title
        WebElement notebookWithTitleDeleteButtonWebElement
                = this.driver.findElement(
                    By.xpath("//div/div[contains(@class, 'qa-name') and text() = '"
                            + notebookTitle
                            + "']/following-sibling::*/following-sibling::*/div[contains(@class, 'qa-deleteButton')]"));

        notebookWithTitleDeleteButtonWebElement.click();

        this.clickConfirmationDialogConfirmButton();

    }

    private void clickTrashCanButton() {
        WebElement trashCanButtonWebElement = this.driver.findElement(
                By.cssSelector(".qa-notebookWidget.qa-trash div div.GOSDSN-CBD.GOSDSN-CGQB"));
        trashCanButtonWebElement.click();
        this.waitForPageToLoad(2);
    }

    public void navigateToTheTrashCan() {
        this.clickNotebooksButton();
        this.clickTrashCanButton();
    }

    public boolean theNoteWithTitleAndBodyIsDisplayed(String noteTitle, String noteBody) {
        List<EvernoteNote> evernoteNoteList = this.getNoteList();

        return evernoteNoteList.contains(new EvernoteNote(noteTitle, noteBody));
    }


    private void clickEmptyTrashButton() {
        WebElement emptyTrashButtonWebElement = this.driver.findElement(
                By.xpath("//*[text()='Empty trash']"));
        emptyTrashButtonWebElement.click();
    }

    public void clickTheEmptyTrashButtonAndEmptyTheTrash() {
        this.clickEmptyTrashButton();
        this.clickConfirmationDialogConfirmButton();
        this.waitForPageToLoad(2);
    }

    // Restore note with given title from trash
    public void restoreTheNoteWithTitle(String noteTitle) {
        WebElement noteRestoreButtonWebElement = this.driver.findElement(
                By.xpath("//div[contains(@class, 'focus-NotesView-Note-innerSnippetContainer')]/div/div[contains(@class, 'qa-title') and text() = '"
                + noteTitle
                + "']/parent::*/following-sibling::*/div/button[text() = 'Restore']"
                ));
        noteRestoreButtonWebElement.click();
    }

    public void navigateToTheNotesList() {
        this.clickNotesButton();
    }

    public void assignTheNoteWithTitleTheTag(String noteTitle, String tag) {
        this.clickOnNoteWithTitle(noteTitle);

        WebElement tagInputWebElement = this.driver.findElement(
                By.xpath("//div[@id='gwt-debug-NoteTagsView-tagInputBox']/input"));

        this.clickWebElementWithJavascript(tagInputWebElement);

        this.driver.switchTo().activeElement().sendKeys(tag + "\n");

        this.clickOnNoteWithTitle(noteTitle);

    }

    private void clickTagsButton() {
        WebElement notesButtonWebElement = this.driver.findElement(By.cssSelector("#gwt-debug-Sidebar-tagsButton-container > div:nth-child(1) > div:nth-child(1) > img:nth-child(2)"));
        this.clickWebElementWithJavascript(notesButtonWebElement);
        this.waitForPageToLoad(1);
    }

    private void clickTagButton(String tag) {
        WebElement tagButtonWebElement = this.driver.findElement(
                By.xpath("//div[contains(@class, 'focus-drawer-TagsDrawer-TagSelectable-name') and text() = '"
                + tag
                + "']"));

        tagButtonWebElement.click();
        this.waitForPageToLoad(1);
    }

    private void clickTagDeleteButton(String tag) {
        WebElement tagDeleteButtonWebElement = this.driver.findElement(
                By.xpath("//div[contains(@class, 'focus-drawer-TagsDrawer-TagSelectable-name') and text() = '"
                        + tag
                        + "']/parent::*"
                        + "/following-sibling::*"
                        + "/div/div[contains(@class, 'focus-drawer-TagsDrawer-TagSelectable-delete-icon')]"
                ));

//        tagDeleteButtonWebElement = tagDeleteButtonWebElement.findElement(By.xpath("//div[contains(@class, 'focus-drawer-TagsDrawer-TagSelectable-delete-icon')]"));

        this.clickWebElementWithJavascript(tagDeleteButtonWebElement);
        this.waitForPageToLoad(1);
    }

    public void navigateToTag(String tag) {
        this.clickTagsButton();
        this.clickTagButton(tag);
    }

    public void deleteTag(String tag) {
        this.clickTagsButton();
        this.clickTagDeleteButton(tag);
        this.clickConfirmationDialogConfirmButton();
    }
}
