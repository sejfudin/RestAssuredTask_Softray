package testCases;
import java.util.UUID;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Create_Collection {
	UUID uuid = UUID.randomUUID(); 														//Generate universally unique identifier
	String url = RestAssured.baseURI = "https://api.getpostman.com/collections"; 		//Endpoint
	String apikey = "PMAK-5f16aea10394e90042c91497-621ebe7939814004dc6e09c983f5dcc571";	//Api Key
	String uniqueName = "\"My Collection - " + uuid;									//Collection unique name

	// Body
	String data = "{ \"collection\": {\r\n" + 
			"    \"info\": {\r\n" + 
			"     \r\n" + 
			"      \"schema\": \"https://schema.getpostman.com/json/collection/v2.0.0/collection.json\",\r\n" + 
			"       \"name\":"   + uniqueName +"\"\r\n" + 
			"    },\r\n" + 
			"    \"item\": []\r\n" + 
			"  }}";

	//Test case for create collection with unique collection name, after that it checking status code
	@Test
	void status_code_should_be_200 () {

		given()
		.contentType("application/json")
		.body(data)
		.queryParam("apikey", apikey)
		.when()
		.post(url)
		.then()
		.statusCode(200);
	}
	
	//Negative Test case for create collection withouth body
		@Test
		void collection_should__not_be_created_negative_test () {

			given()
			.contentType("application/json")
			.queryParam("apikey", apikey)
			.when()
			.post(url)
			.then()
			.statusCode(400)
			.body("error.name", equalTo("paramMissingError"))
			.body("error.message", equalTo("Parameter is missing in the request."));
		}
}
