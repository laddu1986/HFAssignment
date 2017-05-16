/**
 * Task 2 - Java, Rest Assured and Cucumber (API Test)

 

Given you following web-services:

http://services.groupkt.com/country/get/all
http://services.groupkt.com/country/get/iso2code/{COUNTRY_ISO2CODE} (e.g. http://services.groupkt.com/country/get/iso2code/us )
 

Using Cucumber to describe the steps and Rest Assured to executed the Requests, create following automated test scenarios:

Get all countries and validate that US, DE and GB were returned in the response
Get each country (US, DE and GB) individually and validate the response
Try to get information for in-existent countries and validate the response
 */

package HelloFresh.hellofreshassignment.assignment2.runner;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"},
                 glue = {"HelloFresh/hellofreshassignment/assignment2/steps/getcountry"},
                 features = {"src/test/features/GetCountry.feature"}) 
public class TestGetCountries{
	
}
