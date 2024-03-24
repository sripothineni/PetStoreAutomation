package api.test;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests2 {
	
	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		faker= new Faker();
		userPayload= new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		// logs
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testPostUser() {
        logger.info("log :: Creating user");
		Response response = UserEndPoints2.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("log :: User Created");
	}
	
	@Test(priority=2)
	public void testGetUserByName() {
		logger.info("log :: Reading user info");
		Response res = UserEndPoints2.readUser(this.userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info(" log :: User info retrived");
	}
	
	@Test(priority=3)
	public void testUpdateUserByName() {
		//updating firstname and lastname
		logger.info("log :: update user info");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		
		Response res = UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		//checking data after update
		Response afterUpdate= UserEndPoints2.readUser(this.userPayload.getUsername());
		afterUpdate.then().log().all();
		Assert.assertEquals(afterUpdate.jsonPath().get("firstName"), this.userPayload.getFirstName());
		Assert.assertEquals(afterUpdate.jsonPath().get("lastName"), this.userPayload.getLastName());
		logger.info("log :: User info updated");
	}
	
	@Test(priority=4)
	public void testDeleteUserByName() {
		logger.info("log :: Delete user");
		Response res = UserEndPoints2.deleteUser(this.userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("log :: Delete user completed");
	}
	

}
