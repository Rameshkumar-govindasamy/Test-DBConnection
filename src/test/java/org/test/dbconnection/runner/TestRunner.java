package org.test.dbconnection.runner;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
 
@CucumberOptions(features = "src/test/resources/features", glue = "org.test.dbconnection.steps")
public class TestRunner extends AbstractTestNGCucumberTests {
 
}
