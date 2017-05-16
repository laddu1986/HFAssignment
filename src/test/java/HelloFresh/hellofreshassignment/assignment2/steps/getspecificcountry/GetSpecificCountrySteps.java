package HelloFresh.hellofreshassignment.assignment2.steps.getspecificcountry;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class GetSpecificCountrySteps {

	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;

	private String ENDPOINT_GET_COUNTRIES = "http://services.groupkt.com/country/get";


	@Given("a api exists to get countries")
	public void a_country_exists(){
		request = given();
	}

	@When("a user retrieves (.*)")
	public void a_user_retrieves_country(String country){
		response = request.when().get(ENDPOINT_GET_COUNTRIES + "/iso2code/" + country);
		System.out.println("response: " + response.prettyPrint());
	}

	@Then("the status code is (\\d+)")
	public void verify_status_code(int statusCode){
		json = response.then().statusCode(statusCode);
	}

	@And("response includes the following in any order")
	public void response_contains_in_any_order(Map<String,String> responseFields){
		for (Map.Entry<String, String> field : responseFields.entrySet()) {

            json.body(field.getKey(), equalTo(field.getValue()));
			
		}
	}
}