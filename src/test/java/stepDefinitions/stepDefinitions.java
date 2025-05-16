package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;


public class stepDefinitions {
    private Response response;


    @Given("User sends GET request to posts")
    public void user_sends_get_request_to_posts() {
        response= given().baseUri("https://jsonplaceholder.typicode.com").when().get("/posts");
    }

    @Then("response should contain {int} posts")
    public void responseShouldContainPosts(int expectedCount) {
        response.then().assertThat().statusCode(200);
        List<Object> posts= response.jsonPath().getList("$");
        Assert.assertEquals(expectedCount,posts.size());
    }

    @And("Status code should be {int}")
    public void statusCodeShouldBe(int expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode,response.getStatusCode());
    }


    @Given("User sends GET request to posts with ID{int}")
    public void userSendsGETRequestToPostsWithID(int id) {
       response= given().baseUri("https://jsonplaceholder.typicode.com").when().get("/posts/"+id);
    }

    @Then("the title of the post should be {string}")
    public void theTitleOfThePostShouldBe(String expectedTitle) {
        String actualTitle= response.jsonPath().getString("title");
        Assert.assertEquals(expectedTitle,actualTitle);
    }


    @Given("User creates a new post with the {string} ,{string} and ID {int}")
    public void userCreatesANewPostWithTheAndID(String title, String body, int id) {
        Map<String, Object> payload= new HashMap<>();
        payload.put("title", title);
        payload.put("body", body);
        payload.put("userId",id);
        response= given().body(payload).baseUri("https://jsonplaceholder.typicode.com").when().post("/posts");
    }
    @Then("The response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        Assert.assertEquals(statusCode,response.getStatusCode());
    }


    @And("The response should contain a post with ID")
    public void theResponseShouldContainAPostWithID() {
        int id= response.jsonPath().getInt("id");
        Assert.assertTrue("Id should be greater than 100",id>100);
        System.out.println("Post created with id:"+id);
    }

    @Given("User updates post with ID {int} using title {string}, body {string}, and userId {int}")
    public void userUpdatesPostWithIDUsingTitleBodyAndUserId(int id, String title, String body, int userId) {
        Map<String, Object>payload= new HashMap<>();
        payload.put("userId",userId);
        payload.put("id",id);
        payload.put("title",title);
        payload.put("body",body);
        response= given().body(payload).baseUri("https://jsonplaceholder.typicode.com").when().put("/posts/"+id);
        System.out.println(response.jsonPath().getInt("id"));
    }


    @And("The response give id {int}")
    public void theResponseGiveId(int id) {
        int actualId= response.jsonPath().get("id");
        Assert.assertEquals(id,actualId);
    }

    @Given("User Sends DELETE request to posts with ID {int}")
    public void userSendsDELETERequestToPostsWithID(int id) {
        response=given().baseUri("https://jsonplaceholder.typicode.com").when().delete("/posts/"+id);
    }
}
