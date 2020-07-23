package testCases;
import org.testng.annotations.Test;
import org.hamcrest.core.Is;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class All_Collections {

	String url = RestAssured.baseURI = "https://api.getpostman.com/collections";		//Endpoint
	String apikey = "PMAK-5f16aea10394e90042c91497-621ebe7939814004dc6e09c983f5dcc571";	//Api Key

	// Test case for status code
	@Test
	void status_code_should_be_200 () {

		given ()
		.queryParam("apikey", apikey)
		.get (url)
		.then ()
		.assertThat()
		.statusCode(200);
	}

	//Test case for checking is there any collection
	@Test
	void check_the_arrey_isnt_empty () {

		given ()
		.queryParam("apikey", apikey)
		.get (url)
		.then ()
		.assertThat()
		.body("collections.size()",greaterThan(0));
	}

	//Test case for checking collection name
	@Test
	void name_should_be_My_Collection () {

		given ()
		.queryParam("apikey", apikey)
		.get (url)
		.then ()
		.assertThat()
		.body("collections.name",hasItem ("My Collection"));
	}

	//Test case for checking owner
	@Test
	void owner_should_be_10144889 () {

		given ()
		.queryParam("apikey", apikey)
		.get (url)
		.then ()
		.assertThat()
		.body("collections.owner[0]",Is.is ("10144889"));
	}

	//Test case for checking id
	@Test
	void check_the_id () {

		given ()
		.queryParam("apikey", apikey)
		.get (url)
		.then ()
		.assertThat()
		.body("collections.id",hasItem ("ca6a9813-4e18-4f75-938e-cd648bf65768"));
	}

	//Test case for checking uid
	@Test
	void check_the_uid () {

		given ()
		.queryParam("apikey", apikey)
		.get (url)
		.then ()
		.assertThat()
		.body("collections.uid",hasItem ("10144889-ca6a9813-4e18-4f75-938e-cd648bf65768"));
	}
}


