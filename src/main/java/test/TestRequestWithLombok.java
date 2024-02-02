package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class TestRequestWithLombok {
    @Test
    public void createBooking(){

        BookingDates bookingDates = new BookingDates("2024-02-02","2024-02-12");
        Booking booking = new Booking("Ion", "Popescu", 250, false, bookingDates, "breakfast");

        Response resp = given().contentType(ContentType.JSON).
                header("accept","application/json").body(booking).
                post("https://restful-booker.herokuapp.com/booking").then().statusCode(200).extract().response();
        System.out.println(resp.asPrettyString());

        BookingID bookingID = resp.as(BookingID.class);
        System.out.println("------------------------");
        System.out.println(bookingID.toString());

        assertEquals(booking.toString(), bookingID.getBooking().toString());
        System.out.println(bookingID.getBookingid());
    }
}
