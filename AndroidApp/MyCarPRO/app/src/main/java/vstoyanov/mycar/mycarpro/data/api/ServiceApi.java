package vstoyanov.mycar.mycarpro.data.api;

import vstoyanov.mycar.mycarpro.data.model.Service;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServiceApi {

    @POST("/cars/services/{vehicle_id}")
    Call<Service> saveService(@Path("vehicle_id") String vehicleId, @Body Service service);

    @PUT("/cars/services/{vehicle_id}")
    Call<Service> updateService(@Path("vehicle_id") String vehicleId, @Body Service service);
}
