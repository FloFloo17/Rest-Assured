package rest_assured.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.BeforeClass;
import io.restassured.RestAssured;

public class ConfigureRestAssured {
	private static String baseURI;
	private static Integer port;
	private static String basePath;

	@BeforeClass
	public static void configureRestAssured() throws IOException {

		// get properties file path
		String filename = "restAssured.properties";
		InputStream filenamePath = ConfigureRestAssured.class.getClassLoader().getResourceAsStream(filename);

		// load properties file
		Properties propRestAssured = new Properties();
		propRestAssured.load(filenamePath);

		baseURI = propRestAssured.getProperty("baseURI");
		port = Integer.parseInt(propRestAssured.getProperty("port"));
		basePath = propRestAssured.getProperty("basePath");
		
		// set property value for Rest Assured config
		RestAssured.baseURI = baseURI;
		RestAssured.port = port;
		RestAssured.basePath = basePath;

	}

}
