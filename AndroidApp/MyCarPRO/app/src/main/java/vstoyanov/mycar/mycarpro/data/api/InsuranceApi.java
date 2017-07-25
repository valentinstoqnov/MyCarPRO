package vstoyanov.mycar.mycarpro.data.api;

import vstoyanov.mycar.mycarpro.data.model.Insurance;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface InsuranceApi {

    @POST("/cars/insurance/{vehicle_id}")
    Call<Insurance> saveInsurance(@Path("vehicle_id") String vehicleId, @Body Insurance insurance);

    @PUT("/cars/insurance/{vehicle_id}")
    Call<Insurance> updateInsurance(@Path("vehicle_id") String vehicleId, @Body Insurance insurance);
}
