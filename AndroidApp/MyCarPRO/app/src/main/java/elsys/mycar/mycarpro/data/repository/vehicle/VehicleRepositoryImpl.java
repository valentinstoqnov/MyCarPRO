package elsys.mycar.mycarpro.data.repository.vehicle;

import java.io.IOException;
import java.util.List;

import elsys.mycar.mycarpro.data.api.VehicleApi;
import elsys.mycar.mycarpro.data.repository.OnSaveOrUpdateCallback;
import elsys.mycar.mycarpro.data.model.Vehicle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleRepositoryImpl implements VehicleRepository {

    private VehicleApi mVehicleApi;

    public VehicleRepositoryImpl(VehicleApi mVehicleApi) {
        this.mVehicleApi = mVehicleApi;
    }

    @Override
    public void saveVehicle(Vehicle vehicle, final OnSaveOrUpdateCallback<Vehicle> callback) {
        Call<Vehicle> call = mVehicleApi.saveVehicle(vehicle);
        call.enqueue(new retrofit2.Callback<Vehicle>() {
            @Override
            public void onResponse(Call<Vehicle> call, Response<Vehicle> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }else {
                    try {
                        System.out.println("not successful: " + response.message() + " ,@@@ " + response.errorBody().string());
                    } catch (IOException | NullPointerException e) {
                        e.printStackTrace();
                    }
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Vehicle> call, Throwable t) {
                callback.onFailure();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void updateVehicle(String vehicleId, Vehicle vehicle, final OnSaveOrUpdateCallback<Vehicle> callback) {
        Call<Vehicle> call = mVehicleApi.updateVehicle(vehicleId, vehicle);
        call.enqueue(new retrofit2.Callback<Vehicle>() {
            @Override
            public void onResponse(Call<Vehicle> call, Response<Vehicle> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Vehicle> call, Throwable t) {
                callback.onFailure();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void deleteVehicle(String id, final OnDeleteCallback callback) {
        Call<String> call = mVehicleApi.deleteVehicle(id);
        call.enqueue(new retrofit2.Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess();
                }else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void getVehicleById(final String id, final OnVehicleFetchedCallback callback) {
        Call<Vehicle> call = mVehicleApi.getVehicleById(id);
        call.enqueue(new retrofit2.Callback<Vehicle>() {
            @Override
            public void onResponse(Call<Vehicle> call, Response<Vehicle> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Vehicle> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });
        /*Call<List<Vehicle>> call = mVehicleApi.getVehicles();
        call.enqueue(new retrofit2.Callback<List<Vehicle>>() {
            @Override
            public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response) {
                if (response.isSuccessful()) {
                    List<Vehicle> vehicles = response.body();
                    for (Vehicle vehicle : vehicles) {
                        if (vehicle.getId().equals(id)) {
                            callback.onSuccess(vehicle);
                            return;
                        }
                    }
                    callback.onFailure();
                }else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<Vehicle>> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });*/
    }

    @Override
    public void getVehicles(final OnVehiclesFetchedCallback callback) {
        Call<List<Vehicle>> call = mVehicleApi.getVehicles();
        call.enqueue(new retrofit2.Callback<List<Vehicle>>() {
            @Override
            public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response) {
                if (response.isSuccessful()) {
                    List<Vehicle> vehicles = response.body();
                    callback.onSuccess(vehicles);
                }else {
                    try {
                        System.out.println("not successful: " + response.message());
                        System.out.println(" ,@@@ " + response.errorBody().string());
                    } catch (IOException | NullPointerException e) {
                        e.printStackTrace();
                    }
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<Vehicle>> call, Throwable t) {
                callback.onFailure();
                t.printStackTrace();
            }
        });
    }
}
