package rest_assured.test.commons;

import static io.restassured.RestAssured.*;

import org.junit.Test;


public class InvalidResourceIT extends ConfigureRestAssured {
	
	public final static String INVALID_URL = "/invalid-url-for-error" ;
		
	@Test
	public void testPost() {
		// Test 400 ----------------------------------------
		given()
			.contentType(CONTENT_TYPE_JSON)
			.body("")
		.when()
			.post(INVALID_URL)
		.then()
			.statusCode(400);
	}
	
	@Test
	public void testGet() {
		// Test 400 ----------------------------------------
		when()
			.get(INVALID_URL)
		.then()
			.statusCode(400);
	}
	
	@Test
	public void testPut() {		
		// Test 400 ----------------------------------------
		given()
			.contentType(CONTENT_TYPE_JSON)
			.body("")
		.when()
			.put(INVALID_URL)
		.then()
			.statusCode(400);
	}
	
	@Test
	public void testDelete() {
		// Test 400 ----------------------------------------
		when()
			.delete(INVALID_URL)
		.then()
			.statusCode(400);
	}

}
