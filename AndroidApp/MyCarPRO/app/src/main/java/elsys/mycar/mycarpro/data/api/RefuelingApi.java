package elsys.mycar.mycarpro.data.api;

import elsys.mycar.mycarpro.model.Refueling;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RefuelingApi {


    @POST("/cars/fuil/{vehicle_id}")
    Call<Refueling> saveRefueling(@Path("vehicle_id") String vehicleId, @Body Refueling refueling);

    @PUT("/cars/fuil/{vehicle_id}")
    Call<Refueling> updateRefueling(@Path("vehicle_id") String vehicleId, @Body Refueling refueling);
}
