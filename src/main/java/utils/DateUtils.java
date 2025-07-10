package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

public class DateUtils {

    // from "MMM dd yyyy" to "yyyy-MM-dd" format
    public static String convertToIsoDate(String dateString) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(dateString, inputFormatter);
        return date.toString();
    }

    //from "EEE MMM dd HH:mm:ss z yyyy" to "yyyy-MM-dd" format
    public static String convertDateToIsoFormat(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        TemporalAccessor accessor = formatter.parse(dateString);
        LocalDate date = LocalDate.from(accessor);
        return date.toString();
    }

    // from "yyyy-MM-dd" to "MM-dd" format
    public static String convertIsoDateToMMdd(String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd", Locale.ENGLISH);
        return date.format(formatter);
    }

    //checkInDate, checkOutDate in "yyyy-MM-dd" format
    public static int getNights(String checkInDate, String checkOutDate) {
        LocalDate start = LocalDate.parse(checkInDate);
        LocalDate end = LocalDate.parse(checkOutDate);
        return (int) ChronoUnit.DAYS.between(start, end);
    }

    //checkInDate, checkOutDate in "yyyy-MM-dd" format
    public static String convertToIsoDateWithDate(String dateString) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, inputFormatter);
        String formattedDate = date.format(outputFormatter);
        return formattedDate;
    }

    // Convert LocalDateTime to "yyyy-MM-dd HH:mm" format
    public static String convertToDateAndTime( LocalDateTime dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = dateString.format(formatter);
        return formattedDateTime;
    }


}
