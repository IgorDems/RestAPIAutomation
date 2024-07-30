package com.example.APIAutomation.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "C:\\GitRepoTest\\RestAPIAutomation\\src\\test\\java\\com\\example\\APIAutomation\\features",
        glue = "com.example.APIAutomation.stepdefinitions",
        plugin = {"pretty","json:target/cucumber.json"}
)
public class APITestRunner {
}