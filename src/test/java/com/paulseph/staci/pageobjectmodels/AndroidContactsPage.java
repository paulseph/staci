package com.paulseph.staci.pageobjectmodels;

import com.paulseph.staci.driver.AndroidContactsDriver;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Paulseph.Farrugia on 11/01/2016.
 */
public class AndroidContactsPage {
    private AppiumDriver driver = AndroidContactsDriver.getAppiumDriver();

    public void clickOnCreateANewContactButton() {
        // Assumes we are on the main page without any contacts.
        WebElement createANewContactButtonWebElement = driver.findElement(By.id("com.android.contacts:id/create_contact_button"));
        createANewContactButtonWebElement.click();
    }


//    WebElement webElement;
//
//
//        webElement = driver.findElement(By.xpath("android.widget.EditText"));
//        webElement.sendKeys("Foobar");
//
//
//        // Name com.android.contacts:id/contact_editor_fragment
//        // mobile android:id/text1
//        // home number
//        // email
//
//
////        driver.quit();

}
