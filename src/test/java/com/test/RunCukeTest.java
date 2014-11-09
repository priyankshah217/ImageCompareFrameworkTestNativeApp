package com.test;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(glue = "com.test", plugin = { "json:cucumber-report.json",
		"html:cucumber-html-reports" }, features = ".")
public class RunCukeTest {

}
