package RestAssured.Tests;

import org.testng.annotations.Test;

import ResrAssured.Utils.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DeleteAPIRequest extends BaseTest{

	@Test
	public void deleteAPI() {
		
		RestAssured
		.given()
			.contentType(ContentType.JSON)
			.header("Cookie", "token="+GenerateToken.token)
			.baseUri("https://restful-booker.herokuapp.com/booking")
		.when()
			.delete("/{bookingid}", CreateBookingRequest.bookingid)
		.then()
			.assertThat()
			.statusCode(201);
	}
}
