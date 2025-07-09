package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtils {
    // Dùng cho định dạng yyyy/MM/dd
    private static final DateTimeFormatter FORMATTER_SLASH = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    // Dùng cho định dạng yyyy-MM-dd
    private static final DateTimeFormatter FORMATTER_DASH = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Tính số đêm giữa 2 ngày truyền vào dạng String
    public static int calculateNights(String checkin, String checkout, boolean useSlashFormat) {
        DateTimeFormatter formatter = useSlashFormat ? FORMATTER_SLASH : FORMATTER_DASH;

        LocalDate inDate = LocalDate.parse(checkin, formatter);
        LocalDate outDate = LocalDate.parse(checkout, formatter);
        return (int) ChronoUnit.DAYS.between(inDate, outDate);
    }

    // Tính số đêm giữa 2 LocalDate
    public static int calculateNights(LocalDate checkin, LocalDate checkout) {
        return (int) ChronoUnit.DAYS.between(checkin, checkout);
    }

}
