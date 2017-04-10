package elsys.mycar.mycarpro.util;

import android.app.DatePickerDialog;
import android.content.Context;

import java.util.Calendar;
import java.util.Date;

public class DatePickerUtils {

    public static void showDatePicker(Context context, DatePickerDialog.OnDateSetListener listener) {
        Calendar dateTime = Calendar.getInstance();
        int day = dateTime.get(Calendar.DAY_OF_MONTH);
        int month = dateTime.get(Calendar.MONTH);
        int year = dateTime.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, listener, year, month, day);
        datePickerDialog.show();
    }
}
