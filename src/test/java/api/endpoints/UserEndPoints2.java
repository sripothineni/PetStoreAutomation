package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payloads.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

//Created for CURD operation methods to the user API

public class UserEndPoints2 {
	
	// Method created for getting URLS from properties file
	static ResourceBundle getURL(){
		
		// loads properties file from resources names with routes
		ResourceBundle routes = ResourceBundle.getBundle("routes"); 
		return routes;
	}
	

	public static Response createUser(User payload) {
		
		String post_url = getURL().getString("post_url");
		System.out.println("post url : "+post_url);

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)

			.when()
				.post(post_url);

		return response;
	}

	public static Response readUser(String username) {
		
		String get_url = getURL().getString("get_url");
		System.out.println("get url : "+get_url);
		Response response = given()
				.pathParams("username", username)

			.when()
				.get(get_url);

		return response;
	}
	
	public static Response updateUser(String username, User payload) {
		
		String put_url = getURL().getString("put_url");
		System.out.println("put url : "+put_url);
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParams("username", username)
				.body(payload)

			.when()
				.put(put_url);

		return response;
	}
	
	public static Response deleteUser(String username) {

		String delete_url = getURL().getString("delete_url");
		System.out.println("delete url : "+delete_url);
		Response response = given()
				.pathParams("username", username)

			.when()
				.delete(delete_url);

		return response;
	}

}
