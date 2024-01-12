package test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertEquals;

public class RestExample {
    @Test
    public void restExampleTest(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("title","JSON title Irina");
        requestBody.put("body","JSON body");

        File fisier = new File("fisier.json");


        Response response =
                given().header("Content-type", "application/json").
                        header("accept", "applicaiton/json").
                        //example1: body as string:
                        //body("{\"title\":\"TestIrina\",\"body\":\"java stuff\"}").
                        //example2: body as jsonObject
                        //body(requestBody.toJSONString()).
                        //example3: body ca si fisier json
                        body(fisier).
                        when().post("https://keytodorestapi.herokuapp.com/api/save").
                            then().assertThat().statusCode(200).extract().response();
        System.out.println(response.asPrettyString());
        System.out.println(response.asString());

        String info = response.jsonPath().getString("info");
        System.out.println(info);
        assertEquals(info,"Todo saved! Nice job!");
        assertThat(info,is("Todo saved! Nice job!"));
    }
}
