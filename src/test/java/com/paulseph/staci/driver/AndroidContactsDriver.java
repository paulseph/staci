package com.paulseph.staci.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AndroidContactsDriver {
    private static AppiumDriver driver;


    public static void main(String []args) throws Exception {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

//        desiredCapabilities.setCapability("automationName", "Appium");
//        desiredCapabilities.setCapability("newCommandTimeout", "30");
//        desiredCapabilities.setCapability("platformName", "Android");
//        desiredCapabilities.setCapability("platformVersion", "5.1");
        desiredCapabilities.setCapability("deviceName", "Android");
//        desiredCapabilities.setCapability("deviceReadyTimeout", "30");
//        desiredCapabilities.setCapability("app", "Contacts");
        desiredCapabilities.setCapability("appPackage", "com.android.contacts");
        desiredCapabilities.setCapability("appActivity", ".DialtactsContactsEntryActivity");

        URL url = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(url, desiredCapabilities);

        WebElement webElement;

        webElement = driver.findElement(By.id("com.android.contacts:id/create_contact_button"));
        webElement.click();

        webElement = driver.findElement(By.xpath("android.widget.EditText"));
        webElement.sendKeys("Foobar");


        // Name com.android.contacts:id/contact_editor_fragment
        // mobile android:id/text1
        // home number
        // email


//        driver.quit();
    }



//    @AfterClass
//    public static void tearDownClass() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//
//    @Test
//    public void test() throws IOException {
//        WebElement button = driver.findElement(By.name("Accessibility"));
//        button.click();
//    }

}
