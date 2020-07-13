package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import resources.Utils;
import org.junit.runner.RunWith;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddMap;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

@RunWith(Cucumber.class)
public class StepDefination extends Utils {

	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	static String placeId;

	TestDataBuild testDataBuild = new TestDataBuild();

	@Given("Add Place Payload {string} {string} {string}")
	public void add_Place_Payload(String name, String language, String address) throws Throwable {

		res = given().spec(requestSpecification()).body(testDataBuild.AddPlacePayload(name, language, address));

	}

	@When("^User calls \"([^\"]*)\" with \"([^\"]*)\" Http request$")
	public void user_calls_something_with_something_http_request(String resource, String method) throws Throwable {

		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (method.equalsIgnoreCase("POST"))
			response = res.when().post(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response = res.when().post(resourceAPI.getResource());

	}

	@Then("^then API call is success with status code 200$")
	public void then_api_call_is_success_with_status_code_200() throws Throwable {
		assertEquals(response.getStatusCode(), 200);
	}

	@And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void something_in_response_body_is_something(String keyValue, String expectedValue) throws Throwable {
		String resp = response.asString();

		assertEquals(getJsonPath(response, keyValue), expectedValue);

	}

	@And("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws Throwable {

		// 1.request spec
		placeId = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", placeId);
		user_calls_something_with_something_http_request(resource, "GET");
		String actualName=getJsonPath(response, "name");
		assertEquals(actualName,expectedName);
		
	}
	
	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
	    
		res = given().spec(requestSpecification()).body(testDataBuild.deletePlacePayload(placeId));
		
	}
}
