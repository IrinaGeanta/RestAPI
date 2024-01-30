package test;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.BaseTest2;
import utils.DataBuilder;

public class BaseTest2Example extends BaseTest2 {
    String id;

    @Test(priority = 1)
    public void createToDo(){
        Response resp = doRequest("post","", DataBuilder.buildToDo().toJSONString());
        id = resp.jsonPath().getString("id");
    }

    @Test(priority = 2)
    public void readToDo(){
        Response resp = doRequest("get", id, "");
        System.out.println(resp.asPrettyString());
    }
}
