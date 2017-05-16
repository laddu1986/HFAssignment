package HelloFresh.hellofreshassignment.assignment2.steps.postcountry;

import static io.restassured.RestAssured.given;
import java.util.Map;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostCountrySteps {


	private Response response;
	private RequestSpecification request;

	private String ENDPOINT_POST_COUNTRY = "http://services.groupkt.com/country/post";


	@Given("a api exists to add country")
	public void apiExistsToAddCountry(Map<String,String> requestFields){
		request = given().contentType("application/json").body(requestFields);
		
	}

	@When("a user post the request")
	public void userAddsCountry(){
		response = request.when().post(ENDPOINT_POST_COUNTRY);
		System.out.println("response: " + response.prettyPrint());
	}

	@Then("the status code is (\\d+)")
	public void verifyStatusCode(int statusCode){
		response.then().statusCode(statusCode);
	}

}