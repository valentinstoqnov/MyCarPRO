package elsys.mycar.mycarpro.util;

import android.app.Activity;
import android.content.SharedPreferences;

public class TokenUtils {

    public static final String TOKEN = "token";

    public static void saveToken(String token, Activity activity) {
        SharedPreferences sharedPreferences = ProviderUtils.getSharedPreferences(activity);
        sharedPreferences.edit()
                .putString(TOKEN, token)
                .apply();
    }

    public static String getToken(Activity activity) {
        SharedPreferences sharedPreferences = ProviderUtils.getSharedPreferences(activity);
        return sharedPreferences.getString(TOKEN, null);
    }

    public static void deleteToken(Activity activity) {
        SharedPreferences sharedPreferences = ProviderUtils.getSharedPreferences(activity);
        sharedPreferences.edit()
                .remove(TOKEN)
                .apply();
    }
}
