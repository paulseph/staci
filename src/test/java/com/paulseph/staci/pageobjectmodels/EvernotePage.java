package com.paulseph.staci.pageobjectmodels;

import com.paulseph.staci.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    }

    private void clickWebElementWithJavascript(WebElement webElement){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) this.driver;
        javascriptExecutor.executeScript("arguments[0].click();", webElement);
    }

    private void waitForElementToBeClickable(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(this.driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    private void waitForElementToBeClickableAndClick(WebElement webElement) {
        this.waitForElementToBeClickable(webElement);
        webElement.click();
    }

    private WebElement getNewNoteButtonWebElement() {
        return this.driver.findElement(By.cssSelector("#gwt-debug-Sidebar-newNoteButton"));
    }

    private void clickNewNoteButton() {
        WebElement newNoteButtonWebElement = this.getNewNoteButtonWebElement();
        this.waitForElementToBeClickableAndClick(newNoteButtonWebElement);
    }


    private void clickNotesButton() {
        WebElement newNoteWebElement = this.driver.findElement(By.cssSelector("#gwt-debug-Sidebar-notesButton-container > div:nth-child(1) > div:nth-child(1) > img:nth-child(2)"));
        this.clickWebElementWithJavascript(newNoteWebElement);
    }


    private WebElement getNotesHeaderTitleWebElement() {
        return this.driver.findElement(By.cssSelector("#gwt-debug-NotesHeader-title"));
    }


    private void writeNoteTitle(String title) {
        WebElement noteTitleWebElement = this.driver.findElement(By.cssSelector("#gwt-debug-NoteTitleView-textBox"));
        noteTitleWebElement.sendKeys(title);
    }

    private void writeNoteBody(String body) {
        this.driver.switchTo().frame("entinymce_170_ifr");
        WebElement we = this.driver.switchTo().activeElement();
        we.sendKeys(body);
        this.driver.switchTo().defaultContent();
    }

    private void clickNoteDoneButton() {
        WebElement noteDoneButtonWebElement = this.driver.findElement(By.xpath("//button[text()='Done']"));
        this.clickWebElementWithJavascript(noteDoneButtonWebElement);
    }

    private void waitForPageToLoad(int seconds) {
        // A primitive way to ensure that all dynamic content has loaded by simply adding a wait.
        // This should ideally be replaced with better logic.
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllNotes() {
        this.clickNotesButton();
        this.waitForPageToLoad(1);
        while (this.driver.findElements(By.cssSelector(".qa-deleteButton")).size() > 0) {
            WebElement noteDeleteButtonWebElement = this.driver.findElement(By.cssSelector(".qa-deleteButton"));

            Actions action = new Actions(Driver.getWebDriver());
            action.click(noteDeleteButtonWebElement).build().perform();

            WebElement confirmButtonWebElement = this.driver.findElement(By.cssSelector("#gwt-debug-ConfirmationDialog-confirm"));
            confirmButtonWebElement.click();
            this.clickNotesButton();
            this.waitForPageToLoad(1);
        }
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
        this.waitForElementToBeClickableAndClick(accountButtonWebElement);

        WebElement logoutButtonWebElement = this.driver.findElement(By.cssSelector("#gwt-debug-AccountMenu-logout > div:nth-child(2) > div:nth-child(2)"));
        Actions action = new Actions(Driver.getWebDriver());
        action.click(logoutButtonWebElement).build().perform();

    }

}
