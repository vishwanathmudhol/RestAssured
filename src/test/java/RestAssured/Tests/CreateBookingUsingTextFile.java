package RestAssured.Tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import ResrAssured.Utils.BaseTest;
import ResrAssured.Utils.FilesConstants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;



public class CreateBookingUsingTextFile extends BaseTest{

	@Test
	public void postAPIRequest() {
		try {
			String requestBody=FileUtils.readFileToString(new File(FilesConstants.API_File_Path), "UTF-8");
//			System.out.println(requestBody);
			
			Response response=
			RestAssured
			.given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				.baseUri("https://restful-booker.herokuapp.com/booking")
			.when()
				.post()
			.then()
				.assertThat()
				.statusCode(200)
			.extract().response();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
