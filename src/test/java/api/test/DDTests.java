package api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {

@Test(priority=1, dataProvider="Data", dataProviderClass = DataProviders.class)	
public void testPostUsers(String userID, String userName, String password, String firstName, String lastName, String userEmail, String cell) {
	
	User userPayload = new User();
	userPayload.setId(Integer.parseInt(userID));
	userPayload.setUsername(userName);
	userPayload.setPassword(password);
	userPayload.setFirstName(firstName);
	userPayload.setLastName(lastName);
	userPayload.setEmail(userEmail);
	userPayload.setPhone(cell);
	
	Response response = UserEndPoints.createUser(userPayload);
	response.then().log().all();
	Assert.assertEquals(response.getStatusCode(), 200);
}

@Test(priority=2, dataProvider = "UserNames", dataProviderClass = DataProviders.class) 
public void testDeleteUsers(String userName) {
	
	Response response = UserEndPoints.deleteUser(userName);
	response.then().log().all();
	Assert.assertEquals(response.getStatusCode(), 200);

}

}
