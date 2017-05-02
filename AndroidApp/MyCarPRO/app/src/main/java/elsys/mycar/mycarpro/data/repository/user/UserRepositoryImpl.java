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
                try {
                    System.out.println(response.errorBody().string() + " ,! " + response.message());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(response.isSuccessful());
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
/*        AsyncHttpClient client = new AsyncHttpClient();

        RequestParams params = new RequestParams();
        params.put("username", username);
        params.put("password", password);

        client.post("http://myprocar.herokuapp.com/users/login", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String token = null;
                for (Header header : headers) {
                    if (TokenUtils.TOKEN.equals(header.getName())) {
                        token = header.getValue();
                        break;
                    }
                }

                Log.d("LOGIN", "onSuccess: token" + token);
                if (token != null) {
                    callback.onSuccess(token);
                    Log.d("LOGIN", "success");
                }else {
                    Log.d("LOGIN", "fail");
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("LOGIN", "onFailure: fail");
                callback.onFailure();
                error.printStackTrace();
            }
        });*/

        System.out.println("ASDSADASDADSASD");
        Log.d("asda", "asd 0");
        Call<User> call = mUserApi.loginUser(new SimpleUser(username, password));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("asda", "asd 1");
                System.out.println("ASDSADASDADSASD 1111");
                if (response.isSuccessful()) {
                    String token = response.headers().get(TokenUtils.TOKEN);
                    Log.d("asda", "asd 2");
                    System.out.println("ASDSADASDADSASD   " + token);
                    System.out.println("ASDSADASDADSASD 22222");
                    callback.onSuccess(token);
                }else {
                    Log.d("asda", "asd 3");
                    System.out.println("ASDSADASDADSASD 33333");
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("asda", "asd 4");
                System.out.println("ASDSADASDADSASD 44444");
                callback.onFailure();
                t.printStackTrace();
            }
        });
    }
}
