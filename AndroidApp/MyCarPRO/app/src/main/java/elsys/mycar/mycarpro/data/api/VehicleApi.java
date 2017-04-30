package elsys.mycar.mycarpro.data.api;

import java.util.List;

import elsys.mycar.mycarpro.model.Vehicle;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface VehicleApi {

    @POST("/cars/{username}")
    Call<Vehicle> saveVehicle(@Path("username") String username, @Body Vehicle vehicle);

    @GET("/cars/{username}")
    Call<List<Vehicle>> getVehicles(@Path("username") String username);

}
