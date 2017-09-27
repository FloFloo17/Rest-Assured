package rest_assured.test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Test;


public class CarResourceTest extends ConfigureRestAssured {

	@Test
	public void CreateTest() {
		
		// Initialization --------------------------------
		String carJson = "{"
				+ "\"id\": 12345,"
				+ "\"nom\": \"Create test\","
				+ "\"driver_id\": \"\""
				+ "}";
		
		
		// Test -------------------------------------------
		given()
			.contentType("application/json")
			.body(carJson)
		.when()
			.post("/car")
		.then()
			.statusCode(201)
			.body("id", equalTo(12345))
			.body("nom", equalTo("Create test"))
			.body("driver_id", equalTo(null));
		
		
		// Destroy  ---------------------------------------
		when()
			.delete("/car/12345")
		.then()
			.statusCode(204);
		}
	
	@Test
	public void ReadTest() {
		
		// Initialization --------------------------------
		String carJson = "{"
				+ "\"id\": 12345,"
				+ "\"nom\": \"Create test\","
				+ "\"driver_id\": \"\""
				+ "}";
		
		given()
			.contentType("application/json")
			.body(carJson)
		.when()
			.post("/car")
		.then()
			.statusCode(201);
		
		
		// Test -------------------------------------------
		// Get all Cars
		when()
			.get("/car")
		.then()
			.statusCode(200)
			//.content("get(0).id", equalTo(12345)) test faut si il y a déja des valeurs 
			;
		
		// Get one Car with the given id
		when()
			.get("/car/12345")
		.then()
			.statusCode(200)
			.body("id", equalTo(12345))
			.body("nom", equalTo("Create test"))
			.body("driver_id", equalTo(null));
		
		
		// Destroy  ---------------------------------------
		when()
			.delete("/car/12345")
		.then()
			.statusCode(204);
	}
	
	@Test
	public void UpdateTest() {
		
		// Initialization --------------------------------
		String carJson = "{"
				+ "\"id\": 12345,"
				+ "\"nom\": \"Create test\","
				+ "\"driver_id\": \"\""
				+ "}";
		String carJsonUpdate = "{"
				+ "\"id\": 12345," // tester le changement d'id ?
				+ "\"nom\": \"Update test\","
				+ "\"driver_id\": \"\""
				+ "}";
		
		given()
			.contentType("application/json")
			.body(carJson)
		.when()
			.post("/car")
		.then()
			.statusCode(201);
		
		
		// Test -------------------------------------------
		given()
			.contentType("application/json")
			.body(carJsonUpdate)
		.when()
			.put("/car/12345")
		.then()
			.statusCode(200); // ne retourn pas d'objet pour un Update
		
		
		// Destroy  ---------------------------------------
		when()
			.delete("/car/12345")
		.then()
			.statusCode(204);
	}
	
	@Test
	public void DeleteTest() {
		
		// Initialization --------------------------------
		String carJson = "{"
				+ "\"id\": 12345,"
				+ "\"nom\": \"Create test\","
				+ "\"driver_id\": \"\""
				+ "}";
		
		given()
			.contentType("application/json")
			.body(carJson)
		.when()
			.post("/car")
		.then()
			.statusCode(201);
		
		
		// Test -------------------------------------------
		when()
			.delete("/car/12345")
		.then()
			.statusCode(204);
	}

}
