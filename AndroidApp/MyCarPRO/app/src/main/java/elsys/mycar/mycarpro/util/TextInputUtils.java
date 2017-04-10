package elsys.mycar.mycarpro.util;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.widget.AutoCompleteTextView;

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

    public static void setTextToAutoComplete(TextInputLayout inputLayout, String text) {
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) inputLayout.getEditText();
        if (autoCompleteTextView != null) {
            autoCompleteTextView.setText(text);
        }
    }

    public static String getTextFromAutoComplete(TextInputLayout inputLayout) {
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) inputLayout.getEditText();
        return autoCompleteTextView != null ? autoCompleteTextView.getText().toString() : "";
    }
}
