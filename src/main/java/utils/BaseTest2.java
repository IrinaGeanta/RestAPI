package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class BaseTest2 {

    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;


    @BeforeClass
    public static void createSpecification(){
        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://keytodorestapi.herokuapp.com/").
                setBasePath("api/").setContentType(ContentType.JSON).
                addHeader("accept","application/json").
                build();

        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(anyOf(is(200),is(201),is(204))).build();
    }

    public static Response doRequest(String httpMethod, String id, String body){
        Response resp = null;
        switch (httpMethod.toUpperCase()){
            case "GET":
                resp = given().spec(requestSpec).get(id);
                break;
            case "POST":
                resp = given().spec(requestSpec).body(body).post("save");
                break;
            case "PUT":
                resp = given().spec(requestSpec).body(body).put("totos/" + id);
                break;
            case "DELETE":
                resp = given().spec(requestSpec).delete("delete/" + id);
                break;
        }

        if(resp != null){
            resp = resp.then().spec(responseSpec).extract().response();
        }

        return resp;
    }
}
