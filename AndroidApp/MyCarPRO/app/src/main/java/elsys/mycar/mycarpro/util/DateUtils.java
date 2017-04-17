package elsys.mycar.mycarpro.util;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static final DateFormat DATE_MONTH_YEAR_FORMAT = new SimpleDateFormat("dd MMM yyyy");
    private static final DateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");
    private static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd MMM yyyy HH:mm");

    public static String getTextCurrentDate() {
        return DATE_MONTH_YEAR_FORMAT.format(Calendar.getInstance().getTime());
    }

    public static String getTextCurrentTime() {
        return TIME_FORMAT.format(Calendar.getInstance().getTime());
    }

    public static String getTextDateTime(String date, String time) throws ParseException, IllegalArgumentException{
        Calendar parsedDate = Calendar.getInstance();
        parsedDate.setTime(DATE_MONTH_YEAR_FORMAT.parse(date));

        Calendar parsedTime = Calendar.getInstance();
        parsedTime.setTime(TIME_FORMAT.parse(time));

        parsedDate.set(Calendar.HOUR_OF_DAY, parsedTime.get(Calendar.HOUR_OF_DAY));
        parsedDate.set(Calendar.MINUTE, parsedDate.get(Calendar.MINUTE));

        return DATE_TIME_FORMAT.format(parsedDate.getTime());
    }

    public static String textDateFromInts(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return DATE_MONTH_YEAR_FORMAT.format(calendar.getTime());
    }

    public static String textTimeFromInts(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        return TIME_FORMAT.format(calendar.getTime());
    }

    public static String getTextDayFromTextDate(String date) {
        Calendar parsedDate = Calendar.getInstance();
        try {
            parsedDate.setTime(DATE_MONTH_YEAR_FORMAT.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(parsedDate.get(Calendar.DAY_OF_MONTH));
    }

    public static String getTextDayFromTextMonth(String date) {
        Calendar parsedDate = Calendar.getInstance();
        try {
            parsedDate.setTime(DATE_MONTH_YEAR_FORMAT.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(parsedDate.get(Calendar.MONTH));
    }

    public static String getTextDayFromTextYear(String date) {
        Calendar parsedDate = Calendar.getInstance();
        try {
            parsedDate.setTime(DATE_MONTH_YEAR_FORMAT.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(parsedDate.get(Calendar.YEAR));
    }

    public static String parseValidTextDateFromText(String date) throws ParseException{
        Date parsedDate = DATE_MONTH_YEAR_FORMAT.parse(date);
        DATE_MONTH_YEAR_FORMAT.format(parsedDate);

        return DATE_MONTH_YEAR_FORMAT.format(parsedDate);
    }
}
