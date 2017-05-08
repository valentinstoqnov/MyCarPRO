package elsys.mycar.mycarpro.data.api;

import java.util.List;
import elsys.mycar.mycarpro.data.model.Vehicle;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface VehicleApi {

    @POST("/cars")
    Call<Vehicle> saveVehicle(@Body Vehicle vehicle);

    @PUT("/cars/{vehicle_id}")
    Call<Vehicle> updateVehicle(@Path("vehicle_id") String vehicleId, @Body Vehicle vehicle);

    @DELETE("/cars/{vehicle_id}")
    Call<String> deleteVehicle(@Path("vehicle_id") String vehicleId);

    @GET("/car/{vehicle_id}")
    Call<Vehicle> getVehicleById(@Path("vehicle_id") String vehicleId);

    @GET("/cars")
    Call<List<Vehicle>> getVehicles();
}
