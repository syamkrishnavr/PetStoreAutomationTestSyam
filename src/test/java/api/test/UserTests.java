package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {

    Faker faker;
    ObjectMapper objectMapper;
    User userPayload;
    public Logger logger;
    @BeforeClass
    public void setUpData(){

        faker = new Faker();
        userPayload = new User();
        logger = LogManager.getLogger(this.getClass());

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUserName(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
    }

    @Test(priority = 1)
    public void postTestUser() {

        logger.info("**********Creating User************");
        Response response = UserEndPoints.createUser(userPayload);

        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        Gson gson = new Gson();
        String json = gson.toJson(userPayload);
        System.out.println(json);
        logger.info("**********Created User************");
    }

    @Test(priority = 2)
    public void readTestUser(){
        Response response = UserEndPoints.readUser(this.userPayload.getUserName());

        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 3)
    public void updateTestUser() {
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPoints.updateUser(this.userPayload.getUserName(),userPayload);

        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        Gson gson = new Gson();
        String json = gson.toJson(userPayload);
        System.out.println(json);
    }

    @Test(priority = 4)
    public void deleteTestUser(){
        Response response = UserEndPoints.deleteUser(this.userPayload.getUserName());

        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
