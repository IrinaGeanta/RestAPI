package test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.DataBuilder;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CrudUsersTest extends BaseTest {
    String id;
    @Test(priority = 1)
    public void postUser(){
        Response response = doPostRequest("api/users/", DataBuilder.buildUser().toJSONString(),201);
        id = response.jsonPath().getString("result._id");
        System.out.println(response.asPrettyString());
        assertThat(response.jsonPath().getString("msg"),is(equalTo("Successfully added!")));
    }

    @Test(priority = 2)
    public void getUser(){
        Response response = doGetRequest("api/users/"+id,200);
        System.out.println(response.asPrettyString());
        assertThat(response.jsonPath().getString("result._id"),is(equalTo(id)));
    }

    @Test(priority = 3)
    public void updateUser(){
        Response response = doPutRequest("api/users/"+id,DataBuilder.buildUser().toJSONString(),200);
        System.out.println(response.asPrettyString());
        assertThat(response.jsonPath().getString("msg"),is(equalTo("Successfully updated!")));
    }

    @Test(priority = 4)
    public void deleteUser(){
        Response response = doDeleteRequest("api/users/"+id,200);
        System.out.println(response.asPrettyString());
        assertThat(response.jsonPath().getString("msg"),is(equalTo("It has been deleted.")));
    }

}
