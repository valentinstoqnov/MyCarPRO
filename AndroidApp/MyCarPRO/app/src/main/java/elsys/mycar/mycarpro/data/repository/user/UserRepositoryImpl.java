package elsys.mycar.mycarpro.data.repository.user;

import elsys.mycar.mycarpro.data.api.UserApi;
import elsys.mycar.mycarpro.data.model.SimpleUser;
import elsys.mycar.mycarpro.data.model.User;
import elsys.mycar.mycarpro.util.AuthenticationUtils;
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
                    String token = response.headers().get(AuthenticationUtils.TOKEN);
                    callback.onSuccess(token, response.body());
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
