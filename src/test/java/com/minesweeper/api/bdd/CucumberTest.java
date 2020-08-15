package com.minesweeper.api.bdd;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/** To run cucumber test. */
@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features")
//    strict = true,
//    features = "classpath:features",
//    plugin = {"html:target/cucumber-reports"})
public class CucumberTest {

}
