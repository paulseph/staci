package com.paulseph.staci.pageobjectmodels;

import com.paulseph.staci.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EvernoteLoginPage {
    private static String EVERNOTE_LOGIN_URL = "https://www.evernote.com/Login.action?targetUrl=%2FHome.action";

    private WebDriver driver = Driver.getWebDriver();

    public void openLoginPage(){
        this.driver.get(EVERNOTE_LOGIN_URL);
    }

    public void inputUsername(String username) {
        this.driver.findElement(By.id("username")).sendKeys(username);
    }

    public void inputPassword(String password) {
        this.driver.findElement(By.id("password")).sendKeys(password);
    }

    public void pressSignInButton() {
        this.driver.findElement(By.id("login")).click();
    }

    public void signIn(String username, String password) {
        this.inputUsername(username);
        this.inputPassword(password);
        this.pressSignInButton();
    }
}
