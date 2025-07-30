package utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtils {
    public static int calculateNights(LocalDate checkIn, LocalDate checkout) {
        return (int) ChronoUnit.DAYS.between(checkIn, checkout);
    }


}
