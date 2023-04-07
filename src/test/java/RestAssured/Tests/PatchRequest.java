package RestAssured.Tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import ResrAssured.Utils.BaseTest;
import ResrAssured.Utils.FilesConstants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PatchRequest extends BaseTest{

	@Test
	public void patchRequest() {
		try {
			String requestBody = FileUtils.readFileToString(new File(FilesConstants.patchRequestBody),"UTF-8");
			Response respone=
			RestAssured
			.given()
				.contentType(ContentType.JSON)
				.header("Cookie","token="+GenerateToken.token)
				.body(requestBody)
				.baseUri("https://restful-booker.herokuapp.com/booking")
				.when()
					.patch("/{bookingid}", CreateBookingRequest.bookingid)
				.then()
					.assertThat()
					.statusCode(200)
					.body("additionalneeds", Matchers.equalTo("Remote"))
					.extract().response();
			String addnNeeds = respone.path("additionalneeds");
			System.out.println(addnNeeds);
			//			Assert.assertEquals(addnNeeds, "Remote");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
