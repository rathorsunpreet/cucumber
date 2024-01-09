package restApiTest.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;

public class StepDefinitions {

Response response;
String newUrl;
HashMap<Object, Object> map = new HashMap<Object, Object>();
JsonPath jp;

	public StepDefinitions() {
		map.put("name", "morpheus");
		map.put("job", "leader");
		jp = null;
	}

	// Background for features files
	@Given("the correct api endpoint as {string}")
	public void the_correct_api_endpoint_as(String string) {
		RestAssured.baseURI = "https://reqres.in";
		RestAssured.basePath = string;
		newUrl = RestAssured.baseURI + RestAssured.basePath + '/';
	}
	
	@After
	public void tearDown() {
		// Reset response, map and jp at the end of each scenario
		response = null;
		jp = null;
		map.replace("job", "leader");
	}
	
	// GET endpoint /api/users?page=2
	@When("page number {int} is specified")
	public void page_number_number_is_specified(Integer int1) {
		response = given()
				   .queryParam("page", int1)
				   .when()
				   .get();
	}
	@Then("response status code is {int}")
	public void response_status_code_is(Integer int1) {
		assertEquals(int1, response.getStatusCode());
	}
	@Then("response contains data field with length of {int}")
	public void response_contains_data_field_with_length_of(Integer int1) {
		jp = new JsonPath(response.asString());
		assertEquals(int1, jp.getInt("data.size()"));
	}
	
	// GET endpoints /api/users/2 and /api/users/23
	@When("{int} is specified")
	public void is_specified(Integer int1) {
	    response = null;
	    response = get(newUrl + int1);
	}
	@Then("response status is {int}")
	public void response_status_is(Integer int1) {
	    assertEquals(int1, response.getStatusCode());
	}
	@Then("response body contains {string}")
	public void response_body_contains(String string) {
		if (string.length() != 0) {
			jp = new JsonPath(response.asString());
			if (RestAssured.basePath.equalsIgnoreCase("/api/users")) {
				assertEquals(string, jp.getString("data.email"));
			} else {
				// endpoint /api/unknown/2
				assertEquals(string, jp.getString("data.name"));
			}
		} else {
			assertEquals("{}", response.asString());
		}
	}
	
	// POST endpoint /api/users
	@When("the request is sent to the API")
	public void the_request_is_sent_to_the_api() {
	    response = given()
	    		   .contentType(ContentType.JSON)
	    		   .body(map)
	    		   .when()
	    		   .post();	   
	}
	@Then("new user is created with name as {string} and job as {string}")
	public void new_user_is_created_with_name_as_and_job_as(String string, String string2) {
	    jp = new JsonPath(response.asString());
	    assertEquals(string, jp.getString("name"));
	    assertEquals(string2, jp.getString("job"));
	}
	
	// PUT (/api/users/2) and PATCH (/api/users/2) endpoint 
	@When("{int}, {string}, {string} are sent to the API using {string}")
	public void are_sent_to_the_api_using(Integer int1, String string, String string2, String string3) {
	    // Update map -> job to string2 value
		map.replace("job", string2);
		String tempUrl = newUrl + int1;
		
		if (string3.equalsIgnoreCase("PUT")) {
			response = given()
					   .contentType(ContentType.JSON)
					   .body(map)
					   .when()
					   .put(tempUrl);
		} else if (string3.equalsIgnoreCase("PATCH")) {
			response = given()
					   .contentType(ContentType.JSON)
					   .body(map)
					   .when()
					   .patch(tempUrl);
		}
	}
	@Then("response body contains {string} and {string}")
	public void response_body_contains_and(String string, String string2) {
		jp = new JsonPath(response.asString());
		// PUT and PATCH users endpoint
		if (RestAssured.basePath.equalsIgnoreCase("/api/users")) {
		    assertEquals(string, jp.getString("name"));
		    assertEquals(string2, jp.getString("job"));
		} else {
			// endpoints are /api/register and /api/login/
			HashMap<String, String> mp = response.getBody().as(new TypeRef<HashMap<String, String>>() {});
			assertTrue(mp.containsKey(string));
			assertEquals(string2, mp.get(string));
		}
	}
	
	// Delete endpoint /api/users/2
	@When("id of {int} is sent to the API")
	public void id_of_is_sent_to_the_api(Integer int1) {
	    response = delete(newUrl + int1);
	}
	
	// Delay Response endpoint /api/users?delay=3
	@When("page number {int} is specified with delay parameter")
	public void page_number_is_specified_with_delay_parameter(Integer int1) {
		response = given()
				   .queryParam("delay", int1)
				   .when()
				   .get();
	}
	
	// Get endpoint /api/unknown
	@When("endpoint is pinged")
	public void endpoint_is_pinged() {
		if (RestAssured.basePath.equalsIgnoreCase("/api/unknown")) {
			response = get();
		} else {
			// endpoints are /api/register and /api/login/
			response = given()
		    		   .contentType(ContentType.JSON)
		    		   .body(map)
		    		   .when()
		    		   .post();
		}
	}
	
	// POST endpoint /api/register
	@When("the payload consist of {string} and {string}")
	public void the_payload_consist_of_and(String string, String string2) {
	    map.clear();
	    map.put("email", string);
	    if (string2.length() != 0) {
	    	map.put("password", string2);
	    }
	}
}
