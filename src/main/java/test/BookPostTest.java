package test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BookPostTest {

    @Test
    public void bookPostRestAssured(){
        RestAssured.baseURI="https://fakerestapi.azurewebsites.net/api/v1/Books";
        File bookFile = new File("fakeBook.json");
/*
        Response response = given().body(bookFile).
                contentType(ContentType.JSON).
                        post().
                then().statusCode(200).body("info",equalTo("Todo saved! Nice job!")).
                extract().response();

        id = response.jsonPath().getString("id");*/
    }
}
