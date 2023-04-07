package RestAssured.Tests;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import ResrAssured.Utils.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class CreateBookingRequest extends BaseTest {

//	{
//	    "firstname": "Vishwa",
//	    "lastname": "M",
//	    "totalprice": 1128,
//	    "depositpaid": true,
//	    "bookingdates": {
//	        "checkin": "2023-01-01",
//	        "checkout": "2023-01-01"
//	    },
//	    "additionalneeds": "super bowls"
//	}
	
	static int bookingid;
	@Test  
	public void postBookingRequest() {
		
		JSONObject bookingDetails =  new JSONObject();
		JSONObject bookingDates = new JSONObject();
		
		bookingDates.put("checkin", "2023-01-01");
		bookingDates.put("checkout", "2023-01-01");
		
		bookingDetails.put("firstname", "Vishwa");
		bookingDetails.put("lastname", "Mudhol");
		bookingDetails.put("totalprice", 1000);
		bookingDetails.put("depositpaid", true);
		bookingDetails.put("bookingdates", bookingDates);
		bookingDetails.put("additionalneeds", "Rest Assured");
		
		Response response=
		RestAssured
		.given()
			.contentType(ContentType.JSON)
			.body(bookingDetails.toString())
			.baseUri("https://restful-booker.herokuapp.com/booking")
		.when()
			.post()
		.then()
			.assertThat()
			.statusCode(200)
			.body("booking.firstname", Matchers.equalTo("Vishwa"))
			.body("booking.bookingdates.checkin", Matchers.equalTo("2023-01-01"))
			.body("booking.totalprice", Matchers.equalTo(1000))
			.body("booking.depositpaid", Matchers.equalTo(true))
			.extract().response();

		bookingid = response.path("bookingid");
		
	}
}
