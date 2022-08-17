package steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.UserController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import utils.Endpoints;
import utils.TestNGListener;

import static io.restassured.RestAssured.given;

public class MyStepdefs {
    int userID;
    JSONArray jsonArray;
    UserController user,user1,user2;
    JSONObject jsonObject,jsonObject1,jsonObject2;
    JsonPath jsonPath, jsonpath;
    ObjectMapper objectMapper = new ObjectMapper();
    Response response;
    UserController responseUser;

    @Given("user details")
    public void userDetails() {
        jsonObject = (JSONObject) TestNGListener.data.get("createRequest");
    }

    @When("creating a user")
    public void creatingAUser() throws JsonProcessingException {

        user = new UserController((String) jsonObject.get("name"),
                (String) jsonObject.get("address"),
                (Long) jsonObject.get("marks"));
        response = given()
                .body(user)
                .when().post(Endpoints.userEndpoints)
                .then()
                .statusCode(200).extract().response();
        responseUser = objectMapper.readValue(response.asString(), UserController.class);
    }

    @Then("user must be created")
    public void userMustBeCreated() throws JsonProcessingException {
        UserController responseUser = objectMapper.readValue(response.asString(), UserController.class);
        Assert.assertEquals(user.getName(), responseUser.getName());
    }


    @When("creating a user with no marks")
    public void creatingAUserWithNoMarks() throws JsonProcessingException {
        user = new UserController((String) jsonObject.get("name"),
                (String) jsonObject.get("address"),
                0);
        response = given().body(user)
                .when().post(Endpoints.userEndpoints)
                .then().statusCode(200).extract().response();
        responseUser = objectMapper.readValue(response.asString(), UserController.class);
    }

    @When("updating a user")
    public void updatingAUser() throws JsonProcessingException {

        jsonObject = (JSONObject) TestNGListener.data.get("createRequest");
        user = new UserController((String) jsonObject.get("name"),
                (String) jsonObject.get("address"),
                (Long) jsonObject.get("marks"));

        response = given()
                .body(user)
                .when().post(Endpoints.userEndpoints)
                .then()
                .statusCode(200).extract().response();
        objectMapper = new ObjectMapper();
        responseUser = objectMapper.readValue(response.asString(), UserController.class);

        jsonPath = new JsonPath(response.asString());
    }

    @Then("user is updated")
    public void userIsUpdated() throws JsonProcessingException {

        jsonObject = (JSONObject) TestNGListener.data.get("updateRequest");

        user = new UserController(userID = jsonPath.getInt("id"), (String) jsonObject.get("name"),
                (String) jsonObject.get("address"),
                (Long) jsonObject.get("marks"));

        Response putresponse = given()
                .body(user)
                .when().put(Endpoints.userEndpoint1)
                .then()
                .statusCode(200).extract().response();
        jsonPath = new JsonPath(putresponse.asString());
        objectMapper = new ObjectMapper();
        responseUser = objectMapper.readValue(putresponse.asString(), UserController.class);
        Assert.assertEquals(user.getAddress(), responseUser.getAddress());
    }

    @When("update user name")
    public void updateUserWithBlankName() throws JsonProcessingException {
        jsonObject = (JSONObject) TestNGListener.data.get("createRequest");
        user = new UserController((String) jsonObject.get("name"),
                (String) jsonObject.get("address"),
                (Long) jsonObject.get("marks"));

        response = given()
                .body(user)
                .when().post(Endpoints.userEndpoints)
                .then()
                .statusCode(200).extract().response();
        objectMapper = new ObjectMapper();
        responseUser = objectMapper.readValue(response.asString(), UserController.class);

        jsonpath = new JsonPath(response.asString());


    }

    @Then("display error name is required")
    public void displayErrorNameIsRequired() throws JsonProcessingException {
        jsonObject = (JSONObject) TestNGListener.data.get("updateRequest");
        user = new UserController(jsonpath.getInt("id"), (String) jsonObject.get("name"),
                (String) jsonObject.get("address"),
                (Long) jsonObject.get("marks"));

        Response putresponse = given()
                .body(user)
                .when().put(Endpoints.updateUserEndpoint)
                .then()
                .statusCode(200).extract().response();
        jsonpath = new JsonPath(putresponse.asString());
        objectMapper = new ObjectMapper();
        responseUser = objectMapper.readValue(putresponse.asString(), UserController.class);
        Assert.assertEquals(user.getMarks(), responseUser.getMarks());
    }

    @When("update the user address")
    public void updateTheUserAddress() throws JsonProcessingException {
        jsonObject = (JSONObject) TestNGListener.data.get("createRequest");
        user = new UserController((String) jsonObject.get("name"),
                (String) jsonObject.get("address"),
                (Long) jsonObject.get("marks"));

        response = given()
                .body(user)
                .when().post(Endpoints.userEndpoints)
                .then()
                .statusCode(200).extract().response();
        objectMapper = new ObjectMapper();
        responseUser = objectMapper.readValue(response.asString(), UserController.class);

        jsonpath = new JsonPath(response.asString());


    }

    @Then("display error address is required")
    public void displayErrorAddressIsRequired() throws JsonProcessingException {
        jsonObject = (JSONObject) TestNGListener.data.get("updateRequest");
        user = new UserController(jsonpath.getInt("id"), (String) jsonObject.get("name"),
                (String) jsonObject.get("address"),
                (Long) jsonObject.get("marks"));

        Response putresponse = given()
                .body(user)
                .when().put(Endpoints.updateUserEndpoint)
                .then()
                .statusCode(200).extract().response();
        jsonpath = new JsonPath(putresponse.asString());
        objectMapper = new ObjectMapper();
        responseUser = objectMapper.readValue(putresponse.asString(), UserController.class);
        Assert.assertEquals(user.getAddress(), responseUser.getAddress());
    }

    @When("update the user marks")
    public void updateTheUserMarks() throws JsonProcessingException {
        jsonObject = (JSONObject) TestNGListener.data.get("createRequest");
        user = new UserController((String) jsonObject.get("name"),
                (String) jsonObject.get("address"),
                (Long) jsonObject.get("marks"));

        response = given()
                .body(user)
                .when().post(Endpoints.userEndpoints)
                .then()
                .statusCode(200).extract().response();
        objectMapper = new ObjectMapper();
        responseUser = objectMapper.readValue(response.asString(), UserController.class);

        jsonpath = new JsonPath(response.asString());

    }

    @Then("display error marks is required")
    public void displayErrorMarksIsRequired() throws JsonProcessingException {
        jsonObject = (JSONObject) TestNGListener.data.get("updateRequest");
        user = new UserController(jsonpath.getInt("id"), (String) jsonObject.get("name"),
                (String) jsonObject.get("address"),
                (Long) jsonObject.get("marks"));

        Response putresponse = given()
                .body(user)
                .when().put(Endpoints.updateUserEndpoint)
                .then()
                .statusCode(200).extract().response();
        jsonpath = new JsonPath(putresponse.asString());
        objectMapper = new ObjectMapper();
        responseUser = objectMapper.readValue(putresponse.asString(), UserController.class);
        Assert.assertEquals(user.getMarks(), responseUser.getMarks());
    }

    @When("the user is deleted")
    public void theUserIsDeleted() throws JsonProcessingException {
        jsonObject = (JSONObject) TestNGListener.data.get("createRequest");
        user = new UserController((String) jsonObject.get("name"),
                (String) jsonObject.get("address"),
                (Long) jsonObject.get("marks"));
        response = given()
                .body(user)
                .when().post(Endpoints.userEndpoints)
                .then()
                .statusCode(200).extract().response();
        objectMapper = new ObjectMapper();
        responseUser = objectMapper.readValue(response.asString(), UserController.class);

    }


    @Then("user is deleted")
    public void userIsDeleted() {
        jsonpath = new JsonPath(response.asString());
        int userID;
        userID = jsonpath.getInt("id");
        response = given()
                .body(user)
                .when().delete(Endpoints.DeleteUserEndpoint + "/" + userID)
                .then()
                .statusCode(200).extract().response();
    }

    @Then("the user with particular id is displayed")
    public void theUserWithParticularIdIsDisplayed() {
        given().when().get(Endpoints.GetUserEndpoint).then().statusCode(200).extract().response();
    }


    @Then("internal server error is displayed")
    public void internalServerErrorIsDisplayed() {
        given().when().delete(Endpoints.DeleteUserEndpoint2).then().statusCode(500).extract().response();

    }

    @When("creating a multiple users")
    public void creatingAMultipleUsers() {
        jsonArray = (JSONArray) TestNGListener.data.get("MultipleRequest");

        jsonObject = (JSONObject) jsonArray.get(0);
        jsonObject1 = (JSONObject) jsonArray.get(1);
        jsonObject2 = (JSONObject) jsonArray.get(2);

        user = new UserController((String) jsonObject.get("name"),
                (String) jsonObject.get("address"),
                (Long) jsonObject.get("marks"));
        user1 = new UserController((String) jsonObject1.get("name"),
                (String) jsonObject1.get("address"),
                (Long) jsonObject2.get("marks"));
        user2 = new UserController((String) jsonObject2.get("name"),
                (String) jsonObject2.get("address"),
                (Long) jsonObject2.get("marks"));


    }

    @Then("multiple users are created")
    public void multipleUsersAreCreated() {
        UserController[] array = new UserController[3];

        array[0] = user;
        array[1] = user1;
        array[2] = user2;

        response = given().body(array).when().post(Endpoints.userEndpoints3)
                .then().statusCode(200).extract().response();
    }
}
