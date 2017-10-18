package rest_assured.test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Test;

import rest_assured.test.commons.ConfigureRestAssured;


public class CarResourceIT extends ConfigureRestAssured {
	
	// Initialization --------------------------------
	private final static int ID_TEST = 123;
	private long initialCount = 0;
	
	private final static String DATA_BODY = "{"
			+ "\"id\":" + ID_TEST + ","
			+ "\"nom\": \"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\","
			+ "\"driver\": null"
			+ "}";
	
	private final static String DATA_BODY_FOR_UPDATE = "{"
			+ "\"id\":" + ID_TEST + ","
			+ "\"nom\": \"BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB\","
			+ "\"driver\": null"
			+ "}";
	
	@Test
	public void test() {
		System.out.println("--- test CarResource ");
		clear(); // Just to be sure it doesn't exist before insert
		
		try {
			initialCount = when().get("/car.count").then().extract().path("count");
	    	System.out.println("Initial count = " + initialCount );
		} catch (Exception e) {
			System.out.println("Initial count inposible valeur par défault = " + initialCount );
		}
		
		
		//--- CREATE
		System.out.println("Create : " + DATA_BODY);
		testPost();
		
		//--- FIND BY ID
		System.out.println("Find by id" );
		testGetById();
		
    	//--- UPDATE
		System.out.println(" : " + DATA_BODY_FOR_UPDATE );
		testPutForUpdate(200);
		
		//--- DELETE
		System.out.println("Delete : " + DATA_BODY_FOR_UPDATE );
		testDelete(204); // Found and deleted
		testDelete(404); // Not found
		
		//--- NOT FOUND BY ID
		System.out.println("Not found by id" );
		testNotFoundGetById();

    	//--- UPDATE NOT FOUND
		System.out.println("Update Not found" );
		testPutForUpdate(404);
		
    	//--- SAVE
		System.out.println("Save (Update or Insert)");
		testPutForSave();
		
		//--- FIND ALL
		System.out.println("Find all");
		testGetAll();
		
		//--- COUNT
		System.out.println("Count");
		testGetCount();
		
		
		System.out.println("Delete after test");
		clear();
	}
	
	private void testPost() {
		// Test CREATE : 201 expected ----------------------------------------
		given()
			.contentType(CONTENT_TYPE_JSON)
			.body(DATA_BODY)
		.when()
			.post("/car")
		.then()
			.statusCode(201)
			.body("id", equalTo(ID_TEST))
			.body("nom", equalTo("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"))
			.body("driver", equalTo(null));

		// Test 409 ----------------------------------------
		given()
			.contentType(CONTENT_TYPE_JSON)
			.body(DATA_BODY)
		.when()
			.post("/car")
		.then()
			.statusCode(409);
	}
	
	private void testGetById() {	
		// Test 200 ----------------------------------------
		when()
			.get("/car/" + ID_TEST)
		.then()
			.statusCode(200)
			.body("id", equalTo(ID_TEST))
			.body("nom", equalTo("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"))
			.body("driver", equalTo(null));
	}
		
	private void testNotFoundGetById() {	
		// Test 404 ----------------------------------------
		when()
			.get("/car/" + ID_TEST)
		.then()
			.statusCode(404);
	}
	
	private void testPutForUpdate(int expectedStatusCode) {
		given()
			.contentType(CONTENT_TYPE_JSON)
			.body(DATA_BODY_FOR_UPDATE)
		.when()
			.put("/car/" + ID_TEST)
		.then()
			.statusCode(expectedStatusCode);
	}
	
	private void testDelete(int expectedStatusCode) {
		when()
			.delete("/car/" + ID_TEST)
		.then()
			.statusCode(expectedStatusCode);
	}
	
	private void testPutForSave() {
		// Test 201 ----------------------------------------
		given()
			.contentType(CONTENT_TYPE_JSON)
			.body(DATA_BODY)
		.when()
			.put("/car")
		.then()
			.statusCode(201)
			.body("id", equalTo(ID_TEST))
			.body("nom", equalTo("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"))
			.body("driver", equalTo(null));
		
		// Test 200 ----------------------------------------
		given()
			.contentType(CONTENT_TYPE_JSON)
			.body(DATA_BODY_FOR_UPDATE)
		.when()
			.put("/car")
		.then()
			.statusCode(200)
			.body("id", equalTo(ID_TEST))
			.body("nom", equalTo("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"))
			.body("driver", equalTo(null));
	}
	
	private void testGetAll() {
		// Test 200 ----------------------------------------
		when()
			.get("/car")
		.then()
			.statusCode(200);
	}
	
	private void testGetCount() {
		// Test 200 ----------------------------------------
//		when()
//			.get("/car.count")
//		.then()
//			.statusCode(200)
//			.body("count", equalTo(initialCount + 1));		
	}
	
	private void clear() {
		when().delete("/car/" + ID_TEST);
	}

}
