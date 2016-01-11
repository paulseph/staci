package com.paulseph.staci.helper;

import com.paulseph.staci.driver.AndroidContactsDriver;
import com.paulseph.staci.pageobjectmodels.EvernotePage;
import com.paulseph.staci.stepdefinitions.EvernoteSteps;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import com.paulseph.staci.driver.Driver;
import cucumber.api.java.gl.E;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CucumberBeforeAfter {
    private static boolean imagesCleaned = false;

    // This ensures that this @Before is always executed first
    @Before(order = 0, value = "~@androidContacts")
    public void setup() {
        // Delete all screen shots from previous execution
        // THIS SHOULD BE EXECUTED ONLY ONCE
        if (!imagesCleaned) {
            File reportsDirectory = new File("reports/html-reports");
            final File[] files = reportsDirectory.listFiles((dir, name) -> {
                return name.matches(".*.jpeg");
            });
            for (final File file : files) {
                file.delete();
            }
            imagesCleaned = true;
        }

        Driver.setBrowser(System.getProperty("browser"));
        Driver.startWebDriver();
    }

    @Before(value = "@androidContacts")
    public void setupAndroid() {
        AndroidContactsDriver.startWebDriver();
    }

    // This ensures that this @After is always executed last
    @After(order = 0, value = "~@androidContacts")
    public void tearDown(Scenario scenario) {
        // If Cucumber scenario fails, output time of failure and take screen shot
        if (scenario.isFailed()) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            scenario.write("Time of failure: " + dateFormat.format(Calendar.getInstance().getTime()));
            DriverScreenShotHelper.takeScreenShot(scenario);
        }

        if (Driver.getWebDriver() != null) {
            Driver.getWebDriver().quit();
            Driver.nullWebDriver();
        }

    }

    @After(value = "@androidContacts")
    public void shutdownAndroid() {
        AndroidContactsDriver.shutdownWebDriver();
    }




}
