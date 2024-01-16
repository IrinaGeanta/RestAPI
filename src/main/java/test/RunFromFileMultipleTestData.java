package test;

import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import utils.BaseTest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class RunFromFileMultipleTestData extends BaseTest {
    @Test
    public void testJsonFile() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray todoList = (JSONArray) parser.parse(new FileReader("data.json"));

        for(Object todo : todoList){
            JSONObject objTodo = (JSONObject) todo;
            Response resp = doPostRequest("api/save", objTodo.toJSONString(), 200);
            assertThat(resp.jsonPath().getString("info"),is(equalTo("Todo saved! Nice job!")));
            System.out.println(resp.asString());
        }
    }
}
