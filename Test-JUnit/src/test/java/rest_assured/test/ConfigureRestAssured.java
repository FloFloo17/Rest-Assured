package rest_assured.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import io.restassured.RestAssured;

public class ConfigureRestAssured {
	private static Tomcat tomcat;
	private static String baseURI;
	private static Integer port;
	private static String basePath;

	@BeforeClass
	public static void configureRestAssured() throws IOException, LifecycleException {

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

		// Enable logging of both the request and the response if REST Assureds
		// test validation fails
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		startTomcat();
	}

	public static void startTomcat() throws LifecycleException {	
		Connector connector = new Connector("HTTP/1.1");
		connector.setPort(port);
		
		tomcat = new Tomcat();
		tomcat.setPort(port);
		tomcat.setConnector(connector);
		tomcat.start();
//		Host test = tomcat.getHost();
	}

	@AfterClass
	public static void stopTomcat() throws LifecycleException {
		tomcat.stop();
	}

}
