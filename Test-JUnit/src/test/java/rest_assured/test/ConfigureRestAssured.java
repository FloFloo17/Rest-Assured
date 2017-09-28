package rest_assured.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.junit.BeforeClass;
import io.restassured.RestAssured;

public class ConfigureRestAssured {

	@BeforeClass
	public static void configureRestAssured() throws IOException {

		// get properties file path
		String filename = "restAssured.properties";
		InputStream filenamePath = ConfigureRestAssured.class.getClassLoader().getResourceAsStream(filename);
		
		// load properties file
		Properties propRestAssured = new Properties();
		propRestAssured.load(filenamePath);
		
		// set property value for Rest Assured config
		RestAssured.baseURI = propRestAssured.getProperty("baseURI");
		RestAssured.port = Integer.parseInt(propRestAssured.getProperty("port"));
		RestAssured.basePath = propRestAssured.getProperty("basePath");

		// Enable logging of both the request and the response if REST Assureds
		// test validation fails
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

	}
}
