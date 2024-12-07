package api.test;


import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDTests {
//test
    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void postTestUser(String userId, String userName, String firstName, String lastName, String userEmail, String pwd, String ph) {

        User userPayload = new User();
        userPayload.setId(Integer.parseInt(userId));
        userPayload.setUserName(userName);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lastName);
        userPayload.setEmail(userEmail);
        userPayload.setPassword(pwd);
        userPayload.setPhone(ph);

        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 2, dataProvider = "UserNames",dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String userName){

        Response response = UserEndPoints.deleteUser(userName);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
