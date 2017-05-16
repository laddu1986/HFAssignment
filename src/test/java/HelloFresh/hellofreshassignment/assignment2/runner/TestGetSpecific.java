package HelloFresh.hellofreshassignment.assignment2.runner;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.CucumberOptions;

@CucumberOptions(plugin = {"pretty"},
                 glue = {"HelloFresh/hellofreshassignment/assignment2/steps/getspecificcountry"},
                 features = {"src/test/features/GetSpecificCountry.feature"}) 
public class TestGetSpecific extends AbstractTestNGCucumberTests{
	
}
