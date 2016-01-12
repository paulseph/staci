package com.paulseph.staci.pageobjectmodels;

import com.paulseph.staci.driver.AndroidContactsDriver;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Paulseph.Farrugia on 11/01/2016.
 */
public class AndroidContactsPage {
    private AppiumDriver driver = AndroidContactsDriver.getAppiumDriver();

    private static void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void waitForPageToLoad(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void clickOnCreateANewContactButton() {
        WebElement createANewContactButtonWebElement;

        // Check if we are in an empty front page or a page with existing contacts
        if (driver.findElements(By.id("com.android.contacts:id/create_contact_button")).size() > 0) {
            createANewContactButtonWebElement = driver.findElement(By.id("com.android.contacts:id/create_contact_button"));
            createANewContactButtonWebElement.click();
        } else if (driver.findElements(By.id("com.android.contacts:id/floating_action_button")).size() > 0) {
            createANewContactButtonWebElement = driver.findElement(By.id("com.android.contacts:id/floating_action_button"));
            createANewContactButtonWebElement.click();
        } else {
            throw new IllegalStateException("Tried to create a new contact but new contact button not visible.");
        }

        this.waitForPageToLoad(1);
    }

    // Returns a list of EditText widgets on screen
    private List<WebElement> getEditTextWebElementList() {
        return this.driver.findElements(By.xpath("//android.widget.EditText"));
    }

    private WebElement getNameWebElement() {
        return this.driver.findElement(By.xpath("//*[contains(@class, 'android.widget.EditText') and contains(@text, 'Name')]"));
    }

    private WebElement getMobileNumberWebElement() {
        return this.getEditTextWebElementList().get(3);
    }

    private WebElement getHomeNumberWebElement() {
        // Assumes we have inputted a Mobile number
        return this.getEditTextWebElementList().get(4);
    }

    private WebElement getEmailWebElement() {
        // Assumes we have inputted a Mobile number
        return this.getEditTextWebElementList().get(6);
    }

    public void inputTheName(String name) {
        this.getNameWebElement().sendKeys(name);
    }

    public void inputTheMobileNumber(String mobileNumber) {
        this.getMobileNumberWebElement().sendKeys(mobileNumber);
    }

    public void inputTheHomeNumber(String homeNumber) {
        this.getHomeNumberWebElement().sendKeys(homeNumber);
    }

    public void inputTheEmailAddress(String emailAddress) {
        this.getEmailWebElement().sendKeys(emailAddress);
    }

    public void navigateBack() {
        this.driver.navigate().back();
        this.waitForPageToLoad(1);
    }

    WebElement getContactPageNameWebElement() {
        return driver.findElement(By.id("com.android.contacts:id/large_title"));
    }

    WebElement getContactPageMobileNumberWebElement() {
        return this.driver.findElement(
                By.xpath("//*[contains(@class, 'android.widget.TextView') and contains(@text, 'Mobile')]" +
                        "/preceding-sibling::*[contains(@class, 'android.widget.TextView')]"));
    }

    WebElement getContactPageHomeNumberWebElement() {
        return this.driver.findElement(
                By.xpath("//*[contains(@class, 'android.widget.TextView') and contains(@text, 'Home')]" +
                        "/preceding-sibling::*[contains(@class, 'android.widget.TextView')]"));
    }

    public boolean contactPageShowsTheName(String name) {
        return this.getContactPageNameWebElement().getText().equals(name);
    }

    public boolean contactPageShowsTheMobileNumber(String mobileNumber) {
        return this.getContactPageMobileNumberWebElement().getText().equals(mobileNumber);
    }

    public boolean contactPageShowsTheHomeNumber(String homeNumber) {
        return this.getContactPageHomeNumberWebElement().getText().equals(homeNumber);
    }

    public boolean contactPageShowsTheEmailAddress(String emailAddress) {
        return this.driver.findElements(
                By.xpath("//*[contains(@class, 'android.widget.TextView') and contains(@text, 'Home')]" +
                        "/preceding-sibling::*[contains(@class, 'android.widget.TextView') and " +
                        "contains(@text, '" + emailAddress + "')]")).size() > 0;
    }

    // Assumes only one contact is displayed
    public boolean aContactWithNameIsShownInContactsList(String name) {
        return this.driver.findElement(
                By.id("com.android.contacts:id/cliv_name_textview")).getText().equals(name);
    }

    private WebElement getFirstContactShownEntryWebElement() {
        return this.driver.findElement(
                By.id("com.android.contacts:id/cliv_name_textview"));
    }


    private WebElement getContactEditButtonWebElement() {
        return this.driver.findElement(
                By.id("com.android.contacts:id/menu_edit"));
    }

    private WebElement getContactDeleteButtonWebElement() {
        return this.driver.findElement(
                By.id("com.android.contacts:id/menu_delete"));
    }

    private WebElement getConfirmationDialogOkButtonWebElement() {
        return this.driver.findElement(
                By.id("android:id/button1"));
    }


    private boolean contactsAreShown() {
        return this.driver.findElements(
                By.id("com.android.contacts:id/cliv_name_textview")).size() > 0;

    }

    private void deleteFirstContactShown() {
        this.getFirstContactShownEntryWebElement().click();
        this.getContactEditButtonWebElement().click();
        this.getContactDeleteButtonWebElement().click();
        this.getConfirmationDialogOkButtonWebElement().click();
    }

    public void deleteAllContacts() {
        while (this.contactsAreShown()) {
            this.deleteFirstContactShown();
        }
    }

}
