import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MonthlyPeriod {
    public String calculatePeriod(String startDate, String endDate) {
         try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);

            Period period = Period.between(start, end);

            int months = Math.abs(period.getMonths());
            int years = Math.abs(period.getYears());

            StringBuilder result = new StringBuilder();
            if (years >= 1) {
                result.append(years).append(years == 1 ? " year" : " years");
            }
            if (months >= 1) {
                if (!result.isEmpty()) {
                    result.append(" and ");
                }
                result.append(months).append(months == 1 ? " month" : " months");
            }

            return result.toString();

        }
        catch (DateTimeParseException e) {
            return "Error";
        } 
    }
}