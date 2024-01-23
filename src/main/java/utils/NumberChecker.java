package utils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class NumberChecker extends TypeSafeMatcher<String> {
    @Override
    public void describeTo(Description description) {
        description.appendText("expected numbers but got: ");
    }

    @Override
    protected boolean matchesSafely(String s) {
        try{
            //"123" > true
            //"ABC123" > false dc incercam sa il parsam la int
            Integer.parseInt(s);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static Matcher<String> numbersOnly(){
        return new NumberChecker();
    }
}
