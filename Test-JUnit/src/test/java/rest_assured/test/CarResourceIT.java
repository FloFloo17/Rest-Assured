package rest_assured.test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Test;


public class CarResourceIT extends ConfigureRestAssured {
	// Initialization --------------------------------
	String carJson = "{"
			+ "\"id\": 123,"
			+ "\"nom\": \"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\","
			+ "\"driver\": \"\""
			+ "}";
	
	String carJsonUpdate = "{"
			+ "\"id\": 2," // tester le changement d'id ?
			+ "\"nom\": \"BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB\","
			+ "\"driver\": \"\""
			+ "}";
	
	long initialCount = 0;
	
	@Test
	public void testPersistence() {
		System.out.println("--- test CarResource ");
		
		when().delete("/car/123"); // Just to be sure it doesn't exist before insert
		
		try {
			initialCount = when().get("/car.count").then().extract().path("count");
	    	System.out.println("Initial count = " + initialCount );
		} catch (Exception e) {
			System.out.println("Initial count inposible valeur par défault = " + initialCount );
		}
		
		
		//--- CREATE
		System.out.println("Create : " + carJson);
		testPostCar();
		
		//--- FIND BY ID
		System.out.println("Find by id..." );
		testGetCarId();
		
    	//--- UPDATE
		System.out.println("Update : " + carJsonUpdate );
		testPutCarId();
		
		//--- DELETE
		System.out.println("Delete : " + carJsonUpdate );
		testDeleteCarId();
		
    	//--- UPDATE
		System.out.println("Create/Update...");
		testPutCar();
		
		//--- FIND ALL
		System.out.println("Find all");
		testGetCar();
		
		//--- COUNT
		System.out.println("Count");
		testGetCountCar();
		
		
		System.out.println("Delete after test");
		when().delete("/car/123");
	}
	
	private void testPostCar() {
		// Test 201 ----------------------------------------
		given()
			.contentType("application/json")
			.body(carJson)
		.when()
			.post("/car")
		.then()
			.statusCode(201)
			.body("id", equalTo(123))
			.body("nom", equalTo("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"))
			.body("driver", equalTo(null));
		
		// Test 400 ----------------------------------------
		when()
			.put("Error/car/4")
		.then()
			.statusCode(400);

		// Test 409 ----------------------------------------
		given()
			.contentType("application/json")
			.body(carJson)
		.when()
			.post("/car")
		.then()
			.statusCode(409);
	}
	
	private void testGetCarId() {	
		// Test 200 ----------------------------------------
		when()
			.get("/car/123")
		.then()
			.statusCode(200)
			.body("id", equalTo(123))
			.body("nom", equalTo("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"))
			.body("driver", equalTo(null));
		
		// Test 400 ----------------------------------------
		when()
			.get("Error/car/123")
		.then()
			.statusCode(400);
		
		// Test 404 ----------------------------------------
		when()
			.get("/car/0")
		.then()
			.statusCode(404);
	}
	
	
	private void testPutCarId() {
		// Test 200 ----------------------------------------
		given()
			.contentType("application/json")
			.body(carJsonUpdate)
		.when()
			.put("/car/123")
		.then()
			.statusCode(200); // ne retourn pas d'objet pour un Update
		
		// Test 400 ----------------------------------------
		given()
			.contentType("application/json")
			.body(carJsonUpdate)
		.when()
			.put("Error/car/2")
		.then()
			.statusCode(400);
		
		// Test 404 ----------------------------------------
		given()
			.contentType("application/json")
			.body(carJsonUpdate)
		.when()
			.put("/car/0")
		.then()
			.statusCode(404);
	}
	
	private void testDeleteCarId() {
		// Test 204 ----------------------------------------
		when()
			.delete("/car/3")
		.then()
			.statusCode(204);
		
		// Test 400 ----------------------------------------
		when()
			.delete("Error/car/3")
		.then()
			.statusCode(400);
		
		// Test 404 ----------------------------------------
		when()
			.delete("/car/3")
		.then()
			.statusCode(404);
	}


	
	private void testPutCar() {
		// Test 201 ----------------------------------------
		given()
			.contentType("application/json")
			.body(carJson)
		.when()
			.put("/car")
		.then()
			.statusCode(201)
			.body("id", equalTo(123))
			.body("nom", equalTo("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"))
			.body("driver", equalTo(null));
		
		// Test 200 ----------------------------------------
		given()
			.contentType("application/json")
			.body(carJsonUpdate)
		.when()
			.put("/car")
		.then()
			.statusCode(200)
			.body("id", equalTo(123))
			.body("nom", equalTo("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"))
			.body("driver", equalTo(null));
		
		// Test 400 ----------------------------------------
		given()
			.contentType("application/json")
			.body(carJsonUpdate)
		.when()
			.put("Error/car")
		.then()
			.statusCode(400);
	}
	
	private void testGetCar() {
		// Test 200 ----------------------------------------
		when()
			.get("/car")
		.then()
			.statusCode(200);
		
		// Test 400 ----------------------------------------
		when()
			.get("Error/car")
		.then()
			.statusCode(400);
	}
	
	private void testGetCountCar() {
		// Test 200 ----------------------------------------
		when()
			.get("/car.count")
		.then()
			.statusCode(200)
			.body("count", equalTo(initialCount + 1));

		
		// Test 400 ----------------------------------------
		when()
			.get("Error/car.count")
		.then()
			.statusCode(400);
		
		
	}

}
