package com.paulseph.staci.pageobjectmodels;

import com.paulseph.staci.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;

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

    public void login(String username, String password) {
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



    // From below onwards not confirmed

    private void clickNewNote() {
//        WebDriverWait wait = new WebDriverWait(driver, 30);
//        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#gwt-debug-Sidebar-newNoteButton")));

//        WebElement newNoteWebElement = this.driver.findElement(By.cssSelector("img[class=GOSDSN-COQ]"));
        WebElement newNoteWebElement = this.driver.findElement(By.cssSelector("#gwt-debug-Sidebar-newNoteButton"));

//        Actions actions = new Actions(driver);
//
//        actions.moveToElement(newNoteWebElement).click().perform();

//        newNoteWebElement.click();

        JavascriptExecutor js = (JavascriptExecutor) this.driver;
        js.executeScript("arguments[0].click();", newNoteWebElement);
    }

    private void writeNoteTitle(String title) {
        WebElement noteTitleWebElement = this.driver.findElement(By.cssSelector("#gwt-debug-NoteTitleView-textBox"));
        noteTitleWebElement.sendKeys(title + " " + new Date());
    }

    private void writeNoteBody(String body) {
        this.driver.switchTo().frame("entinymce_170_ifr");
        WebElement we = this.driver.switchTo().activeElement();
        we.sendKeys(body + " " + new Date());
        this.driver.switchTo().defaultContent();
    }

    private void clickNoteDoneButton() {
//        WebElement noteDoneButtonWebElement = this.driver.findElement(By.cssSelector("#gwt-debug-NoteAttributes-doneButton"));
        WebElement noteDoneButtonWebElement = this.driver.findElement(By.xpath("//button[text()='Done']"));
        noteDoneButtonWebElement.click();
    }

    public void createANoteWithTitleAndBody(String title, String body) {
        this.clickNewNote();
        this.writeNoteTitle(title);
        this.writeNoteBody(body + " " + 1);
        this.clickNoteDoneButton();

        this.clickNewNote();
        this.writeNoteTitle(title);
        this.writeNoteBody(body + " " + 2);
        this.clickNoteDoneButton();

        this.clickNewNote();
        this.writeNoteTitle(title);
        this.writeNoteBody(body + 3);
        this.clickNoteDoneButton();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
