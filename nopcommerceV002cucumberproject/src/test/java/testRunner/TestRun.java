package testRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions
   (features=  ".//Features",
   glue= {"stepDefinitions"},
   dryRun=false,
   monochrome=true,
   tags= {"@Regression,@Sanity"},
   plugin= {"pretty", "html:test-output","json:target/cucumber-reports/CucumberTestReport.json"}
   
		   
		   ) 


public class TestRun {

}
