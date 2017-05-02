package elsys.mycar.mycarpro.util;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.common.base.Preconditions;

import elsys.mycar.mycarpro.loginscreen.LoginActivity;

public class TokenUtils {

    public static final String TOKEN = "token";

    private SharedPreferences mSharedPreferences;
    private Activity mActivity;

    public TokenUtils(Activity mActivity) {
        mSharedPreferences = ProviderUtils.getTokenSharedPreferences(TOKEN, mActivity);
        this.mActivity = Preconditions.checkNotNull(mActivity);
    }

    public void saveToken(String token) {
        mSharedPreferences.edit().putString(TOKEN, token).commit();
    }

    public void deleteToken() {
        mSharedPreferences.edit().remove(TOKEN).commit();
    }

    public boolean isSavedTokenValid() {
        String savedToken = getToken();
        return StringUtils.checkNotNullOrEmpty(savedToken);
    }

    public boolean isTokenValid(String token) {
        String savedToken = getToken();
        return StringUtils.checkNotNullOrEmpty(savedToken, token) && savedToken.equals(token);
    }

    public void goToLogin() {
        Intent intent = new Intent(mActivity, LoginActivity.class);
        mActivity.startActivity(intent);
        mActivity.finish();
    }

    public boolean checkToken() {
        if (isSavedTokenValid()) {
            return true;
        }else {
            goToLogin();
            return false;
        }
    }

    public String getToken() {
        return mSharedPreferences.getString(TOKEN, null);
    }
}
