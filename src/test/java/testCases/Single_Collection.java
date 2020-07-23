package testCases;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;

public class Single_Collection {
	
	String apikey = "PMAK-5f16aea10394e90042c91497-621ebe7939814004dc6e09c983f5dcc571";	//Api Key
	String uid = "10144889-ca6a9813-4e18-4f75-938e-cd648bf65768";						//uid 
	String url = RestAssured.baseURI = "https://api.getpostman.com/collections/"+uid;		//Endpoint

	// Test case for status code
	@Test
	void status_code_should_be_200 () {

		given ()
		.queryParam("apikey", apikey)
		.queryParam("uid", uid)
		.get (url)
		.then ()
		.assertThat()
		.statusCode(200);
	}
	
	// Negative Test case for checking apikey
			@Test
			void checking_response_without_apikey_negative_test () {

				given ()
				.queryParam("uid", uid)
				.get (url)
				.then ()
				.assertThat()
				.body("error.name", equalTo("AuthenticationError"))
				.body("error.message", equalTo("Invalid API Key. Every request requires a valid API Key to be sent."));
			}
		
	
	// Test case checking postman id
		@Test
		void check_the_postman_id () {

			given ()
			.queryParam("apikey", apikey)
			.queryParam("uid", uid)
			.get (url)
			.then ()
			.assertThat()
			.body("collection.info._postman_id", equalTo("ca6a9813-4e18-4f75-938e-cd648bf65768"));
		}

}
