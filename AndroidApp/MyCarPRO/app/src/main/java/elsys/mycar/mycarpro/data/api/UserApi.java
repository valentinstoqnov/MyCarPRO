package elsys.mycar.mycarpro.data.api;

import elsys.mycar.mycarpro.data.model.SimpleUser;
import elsys.mycar.mycarpro.data.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {

    @POST("/users")
    Call<User> saveUser(@Body User user);

    @POST("/users/login")
    Call<User> loginUser(@Body SimpleUser simpleUser);
}
