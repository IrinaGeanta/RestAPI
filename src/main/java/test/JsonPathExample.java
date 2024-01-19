package test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class JsonPathExample {

    @Test
    public void getAllUsers(){
        Response response = given().get("https://keytrcrud.herokuapp.com/api/users/").then().extract().response();
        System.out.println(response.asString());
        System.out.println(response.jsonPath().getString("users"));

        JsonPath jsonPath = response.jsonPath();
        //get by index
        System.out.println(jsonPath.getString("users[3]"));
        System.out.println(jsonPath.getString("users[3].name"));
        System.out.println(jsonPath.getString("users[3].email"));
        System.out.println(jsonPath.getString("users[3].age"));

        System.out.println(jsonPath.getString("users.size()"));
        System.out.println(jsonPath.getString("users.name"));

        //List<String> allNames = new ArrayList<String>();
        //allNames = jsonPath.getList("users.name");

        List<String> allNames = jsonPath.getList("users.name");
        System.out.println(allNames.get(4));
        System.out.println(jsonPath.getString("users._id"));
        System.out.println(jsonPath.getString("users.gender"));
        System.out.println("+++++++++++++++++++++++++++++++");
        System.out.println("all males: "+jsonPath.getString("users.findAll{it.gender=='m'}"));
        System.out.println("all males :" + jsonPath.getString("users.findAll{it.gender == 'm'}.size()"));

        System.out.println("all males :" + jsonPath.getString("users.findAll{it.gender == 'm'}._id"));

        System.out.println("all males :" + jsonPath.getString("users.findAll{it.gender == 'm'}.name"));

        System.out.println("all males :" + jsonPath.getString("users.findAll{it.gender == 'm' && it.age >=50}.name"));

        System.out.println("++++++++++++++++++++++++++++++++");
        System.out.println("Danyka's id :" + jsonPath.getList("users.findAll{it.name== 'Danyka'}._id"));
        //find intoarce numai un element, daca sunt mai multe arata doar primul
        System.out.println("Danyka's id :" + jsonPath.getString("users.find{it.name== 'Danyka' && it.age==23}._id"));
        System.out.println("Danyka and Cristobal:" + jsonPath.getString("users.findAll{it.name== 'Danyka' || it.name=='Cristobal' && it.age==12}._id"));

        System.out.println("all under 18: " + jsonPath.getList("users.findAll{it.age<18}"));

    }
}
