package elsys.mycar.mycarpro.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static final DateFormat DATE_MONTH_YEAR_FORMAT = new SimpleDateFormat("dd MMM yyyy");

    public static String textDateFromInts(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return DATE_MONTH_YEAR_FORMAT.format(calendar.getTime());
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

    public static String parseValidTextDateFromText(String date) {
        Date parsedDate = new Date();
        try {
            parsedDate = DATE_MONTH_YEAR_FORMAT.parse(date);
            DATE_MONTH_YEAR_FORMAT.format(parsedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return DATE_MONTH_YEAR_FORMAT.format(parsedDate);
    }
}
