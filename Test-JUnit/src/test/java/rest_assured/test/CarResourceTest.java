package rest_assured.test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Test;


public class CarResourceTest extends ConfigureRestAssured {
	
	@Test
	public void testGetCarId() {
		// Initialization --------------------------------
		String carJson = "{"
				+ "\"id\": 1,"
				+ "\"nom\": \"nom test Get Car Id\","
				+ "\"driver_id\": \"\""
				+ "}";
		
		given()
			.contentType("application/json")
			.body(carJson)
		.when()
			.post("/car");
		
		// Test 200 ----------------------------------------
		when()
			.get("/car/1")
		.then()
			.statusCode(200)
			.body("id", equalTo(1))
			.body("nom", equalTo("nom test Get Car Id"))
			.body("driver_id", equalTo(null));
		
		// Test 400 ----------------------------------------
		when()
			.get("Error/car/1")
		.then()
			.statusCode(400);
		
		// Test 404 ----------------------------------------
		when()
			.get("/car/0")
		.then()
			.statusCode(404);
	}
	
	@Test
	public void testPutCarId() {
		// Initialization --------------------------------
		String carJson = "{"
				+ "\"id\": 2,"
				+ "\"nom\": \"nom test Put Car Id\","
				+ "\"driver_id\": \"\""
				+ "}";
		String carJsonUpdate = "{"
				+ "\"id\": 2," // tester le changement d'id ?
				+ "\"nom\": \"Update nom test Put Car Id\","
				+ "\"driver_id\": \"\""
				+ "}";
		
		given()
			.contentType("application/json")
			.body(carJson)
		.when()
			.post("/car");
		
		// Test 200 ----------------------------------------
		given()
			.contentType("application/json")
			.body(carJsonUpdate)
		.when()
			.put("/car/2")
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
	}
	
	@Test
	public void testDeleteCarId() {
		// Initialization --------------------------------
		String carJson = "{"
				+ "\"id\": 3,"
				+ "\"nom\": \"nom test Delete Car Id\","
				+ "\"driver_id\": \"\""
				+ "}";
		
		given()
			.contentType("application/json")
			.body(carJson)
		.when()
			.post("/car");
		
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

	@Test
	public void testPostCar() {
		// Initialization --------------------------------
		String carJson = "{"
				+ "\"id\": 4,"
				+ "\"nom\": \"nom test Post Car\","
				+ "\"driver_id\": \"\""
				+ "}";
		
		// Test 201 ----------------------------------------
		given()
			.contentType("application/json")
			.body(carJson)
		.when()
			.post("/car")
		.then()
			.statusCode(201)
			.body("id", equalTo(4))
			.body("nom", equalTo("nom test Post Car"))
			.body("driver_id", equalTo(null));
		
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
	
	@Test
	public void testPutCar() {
		// Initialization --------------------------------
		String carJson = "{"
				+ "\"id\": 5,"
				+ "\"nom\": \"nom test Put Car\","
				+ "\"driver_id\": \"\""
				+ "}";
		String carJsonUpdate = "{"
				+ "\"id\": 5," // tester le changement d'id ?
				+ "\"nom\": \"Update nom test Put Car\","
				+ "\"driver_id\": \"\""
				+ "}";
		
		given()
			.contentType("application/json")
			.body(carJson)
		.when()
			.post("/car");
		
		// Test 201 ----------------------------------------
		given()
			.contentType("application/json")
			.body(carJson)
		.when()
			.put("/car")
		.then()
			.statusCode(201)
			.body("id", equalTo(5))
			.body("nom", equalTo("nom test Put Car"))
			.body("driver_id", equalTo(null));
		
		// Test 200 ----------------------------------------
		given()
			.contentType("application/json")
			.body(carJsonUpdate)
		.when()
			.put("/car")
		.then()
			.statusCode(200)
			.body("id", equalTo(5))
			.body("nom", equalTo("Update nom test Put Car"))
			.body("driver_id", equalTo(null));
		
		// Test 400 ----------------------------------------
		given()
			.contentType("application/json")
			.body(carJsonUpdate)
		.when()
			.put("Error/car")
		.then()
			.statusCode(400);
	}
	
	@Test
	public void testGetCar() {
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
	
	@Test
	public void testGetCountCar() {
		// Test 200 ----------------------------------------
		when()
			.get("/car.count")
		.then()
			.statusCode(200);
		
		// Test 400 ----------------------------------------
		when()
			.get("Error/car.count")
		.then()
			.statusCode(400);
	}

}
