package RestAssured.Tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import ResrAssured.Utils.BaseTest;
import ResrAssured.Utils.FilesConstants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;



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
			
			JSONArray jsonFname = JsonPath.read(response.body().asString(), "$.booking..firstname");
			String firstName = (String) jsonFname.get(0);
			Assert.assertEquals(firstName, "Vishwanath");
			
			JSONArray jsonCheckIn = JsonPath.read(response.body().asString(), "$.booking.bookingdates..checkin");
			String checkInDate = (String) jsonCheckIn.get(0);
			Assert.assertEquals(checkInDate, "2023-02-01");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
