package utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtils {
    public static long calculateNights(LocalDate checkin, LocalDate checkout) {
        return  ChronoUnit.DAYS.between(checkin, checkout);
    }


}
