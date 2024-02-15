package utils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CargoCapacityChecker extends TypeSafeMatcher<String> {
    @Override
    public void describeTo(Description description) {
        description.appendText("expected capacity bigger than 25 million but got: ");
    }

    @Override
    protected boolean matchesSafely(String s) {
        try{
            if(Integer.parseInt(s) > 25000000){
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    public static Matcher<String> cargoCapacity(){
        return new CargoCapacityChecker();
    }
}
