package com.paulseph.staci.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/com/paulseph/staci/features",
//        tags = "@e1",
//        tags = "@e3",
        glue = "com.paulseph.staci",
        plugin = {"pretty", "junit:reports/junit.xml", "json:reports/cucumber.json", "html:reports/html-reports"},
        monochrome = true)
public class RunCukesTest {
}
