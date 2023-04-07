package RestAssured.Tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import PojoClasses.Booking;
import PojoClasses.BookingDates;
import ResrAssured.Utils.BaseTest;
import ResrAssured.Utils.FilesConstants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class CreateBookingUsingPojoClasses extends BaseTest{

	static int bookingID;
	@Test
	public void createBookingWithPojo() {
		 
		try {
		
			String jsonSchema = FileUtils.readFileToString(new File(FilesConstants.postReqJSONSchema), "UTF-8");
			BookingDates dates=new BookingDates("2023-04-06", "2023-04-07");
			Booking book=new Booking("Vishwanath", "Mudhol", 1500, true, dates, "Fan");
			
//			Seerialization ==>>  Converting class object body to JSON body
			ObjectMapper obj=new ObjectMapper();
			String requestBody = obj.writerWithDefaultPrettyPrinter().writeValueAsString(book);
//			System.out.println(requestBody);
			
//			De-Serialization ==>>> converting Json body to class object
//			Booking bookingDetails = obj.readValue(requestBody, Booking.class);
//			System.out.println(bookingDetails.getFirstname());
//			System.out.println(bookingDetails.getTotalprice());
			Response response = RestAssured
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
			
			bookingID = response.path("bookingid");
			
//			Response getResponse=
			RestAssured
			.given()
				.contentType(ContentType.JSON)
				.baseUri("https://restful-booker.herokuapp.com/booking")
			.when()
				.get("/{bookingid}", bookingID)
			.then()
				.assertThat()
				.statusCode(200)
				.body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
			
			System.out.println(jsonSchema);
//			System.out.println(getResponse.body().asString());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
