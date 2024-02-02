package test;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.NumberChecker;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;
import static utils.NumberChecker.numbersOnly;

public class PeopleTest {

    @Test
    public void verifyPeople(){
        Response response = given().get("https://swapi.dev/api/people/1/").then().extract().response();
        System.out.println(response.asPrettyString());

        assertThat(response.jsonPath().getString("name"), is("Luke Skywalker"));
        assertThat(response.jsonPath().getString("height"),is(greaterThan("171")));
        assertThat(response.jsonPath().getString("mass"),is(lessThan("80")));

        String[] array = {response.jsonPath().getString("hair_color"),
                response.jsonPath().getString("skin_color"),
                response.jsonPath().getString("eye_color")};


       assertThat(array, arrayContaining("blond","fair","blue"));
       assertThat(response.jsonPath().getString("birth_year"), is(not(numbersOnly())));
       //assertThat(response.jsonPath().getList("species"),is(both(instanceOf(Collection.class)).and(is((Object) hasLength(0)))));
       System.out.println(response.jsonPath().getList("species").size());

       assertThat(response.jsonPath().getList("vehicles").size(),equalTo(response.jsonPath().getList("starships").size()));
       assertThat(response.jsonPath().getList("vehicles"),is(not(equalTo(response.jsonPath().getList("starships")))));



    }
}
