package RestAssured.Tests;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import ResrAssured.Utils.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;
import net.minidev.json.parser.JSONParser;

public class GetBookingDetails extends BaseTest{

	@Test
	public void getBookingDetails() {
		Response response = RestAssured
		.given()
			.contentType(ContentType.JSON)
			.pathParam("bookingID", CreateBookingRequest.bookingid)
			.baseUri("https://restful-booker.herokuapp.com/booking")
		.when()
			.get("{bookingID}")
		.then()
			.assertThat()
			.statusCode(200)
			.body("firstname", Matchers.equalTo("Vishwa"))
			.body("lastname", Matchers.equalTo("Mudhol"))
		.extract().response();
		

	}

}
