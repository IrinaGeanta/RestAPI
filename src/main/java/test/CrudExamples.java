package test;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class CrudExamples {

    String id;
    JSONObject body, updatedBody;
    @BeforeClass
    public void setup(){
        RestAssured.baseURI="https://keytodorestapi.herokuapp.com/";
        body = new JSONObject();

        Faker fake = new Faker();

        body.put("title", fake.animal().name());
        body.put("body", fake.chuckNorris().fact());

        updatedBody = new JSONObject();
        updatedBody.put("title", fake.backToTheFuture().character());
        updatedBody.put("body", fake.harryPotter().spell());

    }
    //CREATE
    @Test(priority = 1)
    public void postAToDoMessage(){
        Response response = given().body(body.toJSONString()).
                contentType(ContentType.JSON).
                //header("Content-type","application/json")
                post("api/save").
                then().statusCode(200).body("info",equalTo("Todo saved! Nice job!")).
                extract().response();

        id = response.jsonPath().getString("id");
    }
    //READ
    @Test(priority = 2)
    public void readToDo(){
        Response response = given().get("api/"+id).then().extract().response();
        System.out.println(response.asPrettyString());
    }
    //UPDATE
    @Test(priority = 3)
    public void updateTodo(){
        Response resp =
                given().
                        contentType(ContentType.JSON).
                        body(updatedBody.toJSONString()).
                when().
                        put("api/todos/" + id).
                        then().statusCode(201).extract().response();

        System.out.println(resp.asPrettyString());
    }

    //DELETE
    @Test(priority = 4)
    public void deleteTodo(){
        Response resp = given().delete("api/delete/"+id).
                then().statusCode(200).extract().response();

        System.out.println(resp.asPrettyString());
    }
}
