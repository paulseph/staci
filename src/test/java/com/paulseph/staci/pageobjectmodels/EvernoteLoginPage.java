package com.paulseph.staci.pageobjectmodels;

import com.paulseph.staci.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EvernoteLoginPage {
    private static String EVERNOTE_LOGIN_URL = "https://www.evernote.com/Login.action?targetUrl=%2FHome.action";
    private static String REQUIRED_FIELD_ERROR_MESSAGE = "This is a required field.";
    private static String INCORRECT_USERNAME_OR_PASSWORD_ERROR_MESSAGE = "Incorrect username and/or password.";


    private WebDriver driver = Driver.getWebDriver();

    public void openLoginPage(){
        this.driver.get(EVERNOTE_LOGIN_URL);
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
        return this.loginErrorMessageIsShown(EvernoteLoginPage.REQUIRED_FIELD_ERROR_MESSAGE);
    }

    public boolean incorrectUsernameOrPasswordLoginErrorMessageIsShown(){
        return this.loginErrorMessageIsShown(EvernoteLoginPage.INCORRECT_USERNAME_OR_PASSWORD_ERROR_MESSAGE);
    }

}
