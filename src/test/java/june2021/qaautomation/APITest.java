package june2021.qaautomation;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import june2021.qaautomation.apis.APIEndpoints;
import june2021.qaautomation.apis.JsonPaths;
import june2021.qaautomation.utils.APIUtility;
import june2021.qaautomation.utils.DataUtility;

public class APITest extends BaseAPITest {

	@Test
	public void testConfigurationAPI() {

		Response response = given().spec(loginJsonSpec).when().get(APIEndpoints.configurations);
		System.out.println(response.getBody().asString());

		APIUtility.verifyStatusCode(response);

		assertEquals(APIUtility.getBodyDataUsingJsonPath(response, JsonPaths.currencyCode), "USD");

	}

	@Test
	public void testDashboardAPI() {

		Response response = given().spec(loginJsonSpec).param("status", "completed").when().get(APIEndpoints.dashboard);

		APIUtility.verifyStatusCode(response);

		response.then().assertThat()
				.body(matchesJsonSchema(DataUtility.getDataFromExcel("Schemas", "DashboardAPISchema")));

	}

	@Test
	public void testInvalidCredentials() {

		String loginPayload = DataUtility.getDataFromExcel("Payloads", "IncorrectLoginPayload")
				.replace("$.username", "a@gmail.com").replace("$.password", "a@1233323232");

		Response response = given().spec(commonJsonSpec).body(loginPayload).when().post(APIEndpoints.login);
		APIUtility.verifyStatusCode(response);

	}

}
