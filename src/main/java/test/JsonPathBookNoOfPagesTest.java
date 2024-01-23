package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class JsonPathBookNoOfPagesTest {

    @Test
    public void getBookNoOfPages(){
        Response response = given().
                contentType(ContentType.JSON).
                get("https://fakerestapi.azurewebsites.net/api/v1/Books").
                then().statusCode(200).
                extract().response();
        JsonPath jsonPath = response.jsonPath();
        //System.out.println(response.asPrettyString());

        //System.out.println("books between 1000 - 2000 pages: "+jsonPath.getString("findAll{it.pageCount>1000 && it.pageCount<2000}.size()"));
        assertEquals(jsonPath.getString("findAll{it.pageCount>1000 && it.pageCount<2000}.size()"),"9");
    }
}
