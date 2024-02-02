package test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    String firstname;
    String lastname;
    int totalprice;
    boolean depositpaid;
    BookingDates bookingdates;
    String additionalneeds;
}
