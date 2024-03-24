package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payloads.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

//Created for CURD operation methods to the user API

public class UserEndPoints {

	public static Response createUser(User payload) {

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)

			.when()
				.post(Routes.post_url);

		return response;
	}

	public static Response readUser(String username) {

		Response response = given()
				.pathParams("username", username)

			.when()
				.get(Routes.get_url);

		return response;
	}
	
	public static Response updateUser(String username, User payload) {

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParams("username", username)
				.body(payload)

			.when()
				.put(Routes.put_url);

		return response;
	}
	
	public static Response deleteUser(String username) {

		Response response = given()
				.pathParams("username", username)

			.when()
				.delete(Routes.delete_url);

		return response;
	}

}
