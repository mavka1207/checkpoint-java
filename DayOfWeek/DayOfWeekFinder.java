import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DayOfWeekFinder {
    public String findNextDayOfWeek(String startDate, String dayOfWeek) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(startDate, formatter);

            DayOfWeek targetDay = DayOfWeek.valueOf(dayOfWeek.toUpperCase()); //see tähendab, et muudab koik tähed suureks

            int startValue = date.getDayOfWeek().getValue();
            int targetValue = targetDay.getValue();

            int daysToAdd = (targetValue - startValue + 7) % 7;
            if (daysToAdd == 0) {
                daysToAdd = 7;
            }

            LocalDate nextDay = date.plusDays(daysToAdd);
            return nextDay.toString();

     } catch (Exception e) {
           return "Error";
        }
    }
}
