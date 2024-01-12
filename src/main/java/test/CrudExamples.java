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
    JSONObject body;
    @BeforeClass
    public void setup(){
        RestAssured.baseURI="https://keytodorestapi.herokuapp.com/";
        body = new JSONObject();

        Faker fake = new Faker();

        body.put("title", fake.animal().name());
        body.put("body", fake.chuckNorris().fact());

    }
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
    @Test(priority = 2)
    public void readToDo(){
        Response response = given().get("api/"+id).then().extract().response();
        System.out.println(response.asPrettyString());
    }
}
