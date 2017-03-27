package mycar.elsys.utils;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;

public class TextInputUtils {

    public static String getTextFromTil(TextInputLayout inputLayout) {
        TextInputEditText inputEditText = (TextInputEditText) inputLayout.getEditText();
        return inputEditText == null ? "" : inputEditText.getText().toString();
    }

    public static void setTextToTil(TextInputLayout inputLayout, String text) {
        TextInputEditText inputEditText = (TextInputEditText) inputLayout.getEditText();
        if (inputEditText != null) {
            inputEditText.setText(text);
        }
    }

}
