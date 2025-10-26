

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;


public class AgeFinder {
    public int calculateAge(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate birthday = LocalDate.parse(date, formatter);
            LocalDate today = LocalDate.parse("2024-07-09", formatter);

            Period period = Period.between(birthday, today);

            int age = period.getYears();
            if (age < 0) {
                return -1;
            }
            return age;
        } catch (Exception e) {
            return -1;
        }
    }
}
