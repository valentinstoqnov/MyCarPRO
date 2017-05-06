package elsys.mycar.mycarpro.util;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.common.base.Preconditions;

import elsys.mycar.mycarpro.loginscreen.LoginActivity;

public class AuthenticationUtils {

    public static final String TOKEN = "token";
    public static final String USERNAME = "username";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String EMAIL = "email";

    private SharedPreferences mSharedPreferences;
    private Activity mActivity;

    public AuthenticationUtils(Activity mActivity) {
        mSharedPreferences = ProviderUtils.getTokenSharedPreferences(TOKEN, mActivity);
        this.mActivity = Preconditions.checkNotNull(mActivity);
    }

    public void saveUser(String token, String username, String firstName, String lastName, String email) {
        mSharedPreferences.edit()
                .putString(TOKEN, token)
                .putString(USERNAME, username)
                .putString(FIRST_NAME, firstName)
                .putString(LAST_NAME, lastName)
                .putString(EMAIL, email)
                .commit();
    }

    public void deleteUser() {
        mSharedPreferences.edit()
                .remove(TOKEN)
                .remove(USERNAME)
                .remove(FIRST_NAME)
                .remove(LAST_NAME)
                .remove(EMAIL)
                .apply();
        goToLogin();
    }

    public boolean isSavedUserValid() {
        String savedToken = getToken();

        return StringUtils.checkNotNullOrEmpty(savedToken);
    }

    public boolean checkUser() {
        if (isSavedUserValid()) {
            return true;
        }else {
            goToLogin();
            return false;
        }
    }

    public String getToken() {
        return mSharedPreferences.getString(TOKEN, null);
    }

    public String getUsername() {
        return mSharedPreferences.getString(USERNAME, null);
    }

    public String getFirstName() {
        return mSharedPreferences.getString(FIRST_NAME, null);
    }

    public String getLastName() {
        return mSharedPreferences.getString(LAST_NAME, null);
    }

    public String getEmail() {
        return mSharedPreferences.getString(EMAIL, null);
    }

    private void goToLogin() {
        Intent intent = new Intent(mActivity, LoginActivity.class);
        mActivity.startActivity(intent);
        mActivity.finish();
    }
}
