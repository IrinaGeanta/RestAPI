package test;

import io.restassured.path.json.JsonPath;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.List;

public class JsonPeopleTest {

    @Test
    public void getInfoOnPeople() throws FileNotFoundException {
        FileReader file = new FileReader("people.json");
        JsonPath jsonPath = JsonPath.from(file);

        System.out.println("Bob's age :" + jsonPath.getString("people.find{it.name== 'Bob'}.age"));

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");


        System.out.println("Alice's Oradea address: " + jsonPath.getList("people.findAll{it.name== 'Alice'}.addresses[0].findAll{it.city=='Oradea'}.street"));


    }
}
