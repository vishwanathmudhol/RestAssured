package RestAssured.Tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;
import org.testng.util.RetryAnalyzerCount;

import com.jayway.jsonpath.JsonPath;

import ResrAssured.Utils.BaseTest;
import ResrAssured.Utils.FilesConstants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GenerateToken extends BaseTest {

	static String token;
	@Test 
	public void tokenGeneration() {
		try {
			String requestBody = FileUtils.readFileToString(new File(FilesConstants.tokenGeneration), "UTF-8");
			
			Response response=
			RestAssured
			.given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				
				.baseUri("https://restful-booker.herokuapp.com/auth")
			.when()
				.post()
			.then()
				.assertThat()
				.statusCode(200)
			.extract().response();
			
//			token = JsonPath.read(response.body().asString(), "$.token");
			token = response.path("token");
			System.out.println(token);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
