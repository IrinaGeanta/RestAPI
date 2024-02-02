package test;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class StarshipTest {

    @Test
    public void starshipTest(){
        Response response = given().get("https://swapi.dev/api/starships/3/").then().extract().response();
        System.out.println(response.asPrettyString());
    }
}
