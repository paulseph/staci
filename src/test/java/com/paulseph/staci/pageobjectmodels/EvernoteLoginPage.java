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

    public boolean isGoogleSearchTextBarDisplayed() {
        return driver.findElement(By.id("lst-ib")).isDisplayed();
    }

    public boolean isGoogleSearchButtonDisplayed() {
        return driver.findElement(By.name("btnK")).isDisplayed();
    }

    public boolean isGoogleSearchImFeelingLuckyButtonDisplayed() {
        return driver.findElement(By.name("btnI")).isDisplayed();
    }
}
