import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class ProjectTime {
    private String startTime;
    private String endTime;
    private float hoursLogged; // храню минуты, но тип оставляю как в задании

    private static final SimpleDateFormat DATE_FORMAT;
    static {
        DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        DATE_FORMAT.setLenient(false); // строгий парсинг
    }

    public ProjectTime(String start, String end) {
        this.startTime = start;
        this.endTime = end;
        updateHoursLogged();
    }

    public void setStartTime(String start) {
        this.startTime = start;
        updateHoursLogged();
    }

    public void setEndTime(String end) {
        this.endTime = end;
        updateHoursLogged();
    }

    public String getStartTime() { return this.startTime; }
    public String getEndTime()   { return this.endTime; }

    public String getHoursLogged() {
        // -1 — едиственный допустимый отрицательный сценарий (ошибка)
        if (hoursLogged < 0) return "-1";

        long minutes = (long) hoursLogged;

        if (minutes < 120) {
            return minutes + " m";
        }

        long hours = minutes / 60;
        if (hours < 120) {
            return hours + " h";
        }

        long days = hours / 24;
        if (days < 120) {
            return days + " d";
        }

        long months = days / 30; // грубая оценка по 30 дней на месяц — достаточно для задачи
        return months + " mo";
    }

    private void updateHoursLogged() {
        try {
            if (startTime == null || endTime == null ||
                startTime.isEmpty() || endTime.isEmpty()) {
                hoursLogged = -1;
                return;
            }

            Date start = DATE_FORMAT.parse(startTime);
            Date end   = DATE_FORMAT.parse(endTime);

            long diffMillis = end.getTime() - start.getTime();
            if (diffMillis < 0) { // будущая дата начала/конца перепутана
                hoursLogged = -1;
                return;
            }

            // считаем в минутах (так проще форматировать в m/h/d/mo)
            hoursLogged = diffMillis / (60f * 1000f);
        } catch (ParseException e) {
            hoursLogged = -1;
        } catch (Exception e) { // на случай NPE и прочего
            hoursLogged = -1;
        }
    }
}

