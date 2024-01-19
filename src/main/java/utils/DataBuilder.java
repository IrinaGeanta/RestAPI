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

    public static JSONObject buildUser(){
        JSONObject todoBuilder = new JSONObject();
        Faker fake = new Faker();

        todoBuilder.put("name", fake.artist().name());
        todoBuilder.put("email", fake.artist().name() + "@email.com");
        todoBuilder.put("age", 20);
        todoBuilder.put("gender", "f");
        return todoBuilder;
    }
}
