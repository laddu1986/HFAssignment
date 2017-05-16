package HelloFresh.hellofreshassignment.assignment2.runner;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.CucumberOptions;

@CucumberOptions(plugin = {"pretty"},
                 glue = {"HelloFresh/hellofreshassignment/assignment2/steps/postcountry"},
                 features = {"src/test/features/PostCountry.feature"}) 
public class TestPostCountry extends AbstractTestNGCucumberTests{
	
}
