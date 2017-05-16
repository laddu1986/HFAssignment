package HelloFresh.hellofreshassignment.assignment2.runner;

import cucumber.api.testng.AbstractTestNGCucumberTests;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"},
                 glue = {"HelloFresh/hellofreshassignment/assignment2/steps/getallcountries"},
                 features = {"src/test/features/GetAllCountries.feature"}) 
public class TestGetAll extends AbstractTestNGCucumberTests{
	
}
