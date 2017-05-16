package HelloFresh.hellofreshassignment.assignment2.steps.getcountry;

import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import org.junit.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetCountriesSteps {

	private Response response;
	private RequestSpecification request;

	private String ENDPOINT_GET_COUNTRIES = "http://services.groupkt.com/country/get";


	@Given("there is api to get countries")
	public void apiExistsToRetrieveCountries(){
		request = given();
	}
	
	@When("a user try to retireve all countries")
	public void userRetrievesAllCountries(){
		response = request.when().get(ENDPOINT_GET_COUNTRIES + "/all");
		System.out.println("response: " + response.prettyPrint());
	}

	@When("a user try to retireve specific country with \"([^\"]*)\"")
	public void userRetrievesSpecificCountry(String isocode){
		response = request.when().get(ENDPOINT_GET_COUNTRIES + "/iso2code/" + isocode);
		System.out.println("response: " + response.prettyPrint());
	}	

	@Then("the status code is (\\d+)")
	public void verifyStatusCode(int statusCode){
		response.then().statusCode(statusCode);
	}

	@And("response should includes \"([^\"]*)\" and \"([^\"]*)\"")
	public void responseContainsCorrectCodeAndCountryName(String code, String countryName){
	
			String alpha2code =response.getBody().jsonPath().getJsonObject("RestResponse.result.alpha2_code");
			Assert.assertTrue(alpha2code.equals(code));
			String countryNameInResponse =response.getBody().jsonPath().getJsonObject("RestResponse.result.name");
			Assert.assertTrue(countryNameInResponse.equals(countryName));
			
	}
	
	@And("response for in existent country should includes \"([^\"]*)\"")
	public void responseContainsErrorMessage(String errorMessage){
	
			String errorMessageInRes =response.getBody().jsonPath().getJsonObject("RestResponse.messages[1] ");
			Assert.assertTrue(errorMessageInRes.equals(errorMessage));
		
	}
	
	@And("response should include all these \"([^\"]*)\" and \"([^\"]*)\"")
	public void responseContainsAllCountries(String countryCode, String name){
	
		ArrayList<String> alpha2CodeList =response.getBody().jsonPath().getJsonObject("RestResponse.result.alpha2_code");
		Assert.assertTrue(alpha2CodeList.contains(countryCode));
		ArrayList<String> nameList =response.getBody().jsonPath().getJsonObject("RestResponse.result.name");
		Assert.assertTrue(nameList.contains(name));
		
	}
}