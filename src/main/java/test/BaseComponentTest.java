package test;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.DataBuilder;

import static org.testng.Assert.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class BaseComponentTest extends BaseTest {
    String id;
    @Test(priority = 1)
    public void postAToDo(){
        Response resp = doPostRequest("api/save", DataBuilder.buildToDo().toJSONString(),200);
        id = resp.jsonPath().getString("id");

        assertEquals(resp.jsonPath().getString("info"),"Todo saved! Nice job!"); //TestNG assert
        assertThat(resp.jsonPath().getString("info"),is(equalTo("Todo saved! Nice job!"))); //Hamcrest assert

    }

    @Test(priority = 2)
    public void getAToDo(){
        Response resp = doGetRequest("api/" + id, 200);
        assertThat(resp.jsonPath().getString("_id"),is(id));
    }

}
