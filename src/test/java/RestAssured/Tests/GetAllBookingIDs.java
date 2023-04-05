package RestAssured.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetAllBookingIDs {

	@Test
	public void getAllBookingID() {
		Response response = RestAssured
		.given()
		.baseUri("https://restful-booker.herokuapp.com/booking")
		.when()
			.get()
		.then()
			.assertThat()
			.statusCode(200)
			.statusLine("HTTP/1.1 200 OK")
			.header("Content-Type", "application/json; charset=utf-8")
			.extract().response();
		
		Assert.assertTrue(response.getBody().asString().contains("bookingid"));
	}
}
