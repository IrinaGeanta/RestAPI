package test;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static utils.CargoCapacityChecker.*;

public class StarshipTest {

    @Test
    public void starshipTest(){
        Response response = given().get("https://swapi.dev/api/starships/3/").then().extract().response();
        System.out.println(response.asPrettyString());
        List<String> films = response.jsonPath().getList("films");

        assertThat(films, either(hasItem("https://swapi.dev/api/films/2/")).or(hasItem("https://swapi.dev/api/films/6/")).or(hasItem("https://swapi.dev/api/films/5/")));

        assertThat(response.jsonPath().getList("pilots"), is(emptyCollectionOf(String.class)));
        assertThat(films, is(not(emptyCollectionOf(String.class))));

        assertThat(response.jsonPath().getString("model"), both(containsString("Imperial")).and(containsString("Star Destroyer")));
        assertThat(response.jsonPath().getString("cargo_capacity"), cargoCapacity());

    }
}
