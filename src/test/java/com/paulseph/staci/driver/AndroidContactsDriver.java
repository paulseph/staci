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
    private static AppiumDriver driver = null;

    private AndroidContactsDriver() {

    }

    public static AppiumDriver getAppiumDriver() {
        if (driver == null) {
            throw new IllegalStateException("Appium Driver is not initialised!");
        }
        return driver;
    }

    public static void startWebDriver() {
        if (driver != null) {
            throw new IllegalStateException("Appium Driver has already been initialised!");
        } else try {

            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

            desiredCapabilities.setCapability("deviceName", "Android");
            desiredCapabilities.setCapability("appPackage", "com.android.contacts");
            desiredCapabilities.setCapability("appActivity", ".DialtactsContactsEntryActivity");

//        desiredCapabilities.setCapability("automationName", "Appium");
        desiredCapabilities.setCapability("newCommandTimeout", "30");
//        desiredCapabilities.setCapability("platformName", "Android");
//        desiredCapabilities.setCapability("platformVersion", "5.1");
//        desiredCapabilities.setCapability("deviceReadyTimeout", "30");
//        desiredCapabilities.setCapability("app", "Contacts");


            URL url = new URL("http://127.0.0.1:4723/wd/hub");

            driver = new AndroidDriver(url, desiredCapabilities);

        } catch (Exception exception) {
            throw new RuntimeException("Failed to initialize Appium Driver!", exception);
        }
//
    }

    public static void shutdownWebDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
