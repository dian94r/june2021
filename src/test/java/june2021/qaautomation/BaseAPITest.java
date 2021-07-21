package june2021.qaautomation;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeSuite;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import june2021.qaautomation.apis.APIEndpoints;
import june2021.qaautomation.apis.JsonPaths;
import june2021.qaautomation.utils.APIUtility;
import june2021.qaautomation.utils.DataUtility;

public abstract class BaseAPITest {

	RequestSpecification commonJsonSpec = new RequestSpecBuilder()
			.setBaseUri(DataUtility.getDataFromExcel("Config", "BaseUrlAPI")).setContentType(ContentType.JSON).build()
			.log().all();
	RequestSpecification loginJsonSpec;

	@BeforeSuite
	public void login() {
		String loginPayload = DataUtility.getDataFromExcel("Payloads", "LoginPayload");

		Response response = given().spec(commonJsonSpec).body(loginPayload).when().post(APIEndpoints.login);

		APIUtility.verifyStatusCode(response);
		String token = APIUtility.getBodyDataUsingJsonPath(response, JsonPaths.authToken);
		loginJsonSpec = new RequestSpecBuilder().setBaseUri(DataUtility.getDataFromExcel("Config", "BaseUrlAPI"))
				.setContentType(ContentType.JSON).addHeader("authtoken", token).build().log().all();

	}

}
