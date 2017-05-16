package HelloFresh.hellofreshassignment.assignment2.runner;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"},
                 glue = {"HelloFresh/hellofreshassignment/assignment2/steps/postcountry"},
                 features = {"src/test/features/PostCountry.feature"}) 
public class TestPostCountry {
	
}
