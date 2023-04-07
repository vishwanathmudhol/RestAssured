package RestAssured.Tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import ResrAssured.Utils.BaseTest;
import ResrAssured.Utils.FilesConstants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class UpdateBookingDetaiils extends BaseTest{

	
	@Test 
	public void updateBooking() {
		try {
			String requestBody = FileUtils.readFileToString(new File(FilesConstants.updateBooking),"UTF-8");
			System.out.println(GenerateToken.token +"   "+CreateBookingRequest.bookingid);
			
			RestAssured
			.given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				.header("Cookie", "token="+GenerateToken.token)
				.baseUri("https://restful-booker.herokuapp.com/booking")
			.when()
				.put("/{bookingid}", CreateBookingRequest.bookingid)
			.then()
				.assertThat()
				.statusCode(200);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
