package elsys.mycar.mycarpro.data.repository.user;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.IOException;

import cz.msebera.android.httpclient.Header;
import elsys.mycar.mycarpro.data.api.UserApi;
import elsys.mycar.mycarpro.model.SimpleUser;
import elsys.mycar.mycarpro.model.User;
import elsys.mycar.mycarpro.util.TokenUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepositoryImpl implements UserRepository {

    public UserApi mUserApi;

    public UserRepositoryImpl(UserApi mUserApi) {
        this.mUserApi = mUserApi;
    }

    @Override
    public void saveUser(final User user, final OnUserSavedCallback callback) {
        Call<User> call = mUserApi.saveUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User responseUser = response.body();
                    callback.onSuccess(responseUser);
                }else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onFailure();
                t.printStackTrace();
                System.out.println(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void loginUser(String username, String password, final OnUserLoggedInCallback callback) {
        Call<User> call = mUserApi.loginUser(new SimpleUser(username, password));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    String token = response.headers().get(TokenUtils.TOKEN);
                    callback.onSuccess(token);
                }else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onFailure();
                t.printStackTrace();
            }
        });
    }
}
