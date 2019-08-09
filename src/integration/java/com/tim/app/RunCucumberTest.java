package com.tim.app;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features = {"src/integration/resources/features"}, glue = {"com.tim.app"})
public class RunCucumberTest {
}
