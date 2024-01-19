package test;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class BookPostTest {

    @Test
    public void bookPostRestAssured() throws FileNotFoundException {
        File bookFile = new File("fakeBook.json");

        Response response = given().body(bookFile).
                contentType(ContentType.JSON).
                        post("https://fakerestapi.azurewebsites.net/api/v1/Books").
                then().statusCode(200).
                extract().response();

        String id = response.jsonPath().getString("id");
        assertEquals(id, "6");

    }
}
