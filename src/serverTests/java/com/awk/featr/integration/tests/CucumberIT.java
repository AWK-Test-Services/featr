package com.awk.featr.integration.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"},
		features = "src/serverTests/stories",
		strict=true,
		glue = "steps",
		tags = { }
	)
public class CucumberIT {
}
