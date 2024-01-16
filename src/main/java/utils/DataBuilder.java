package utils;

import com.github.javafaker.Faker;
import org.json.simple.JSONObject;

public class DataBuilder {

    public static JSONObject buildToDo(){
        JSONObject todoBuilder = new JSONObject();
        Faker fake = new Faker();

        todoBuilder.put("title", fake.animal().name());
        todoBuilder.put("body", fake.chuckNorris().fact());
        return todoBuilder;
    }
}
