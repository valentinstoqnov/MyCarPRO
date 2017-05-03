package elsys.mycar.mycarpro.data;

import com.google.common.base.Preconditions;

import java.util.List;

import elsys.mycar.mycarpro.data.api.VehicleApi;
import elsys.mycar.mycarpro.model.Insurance;
import elsys.mycar.mycarpro.model.Refueling;
import elsys.mycar.mycarpro.model.Service;
import elsys.mycar.mycarpro.model.Vehicle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleApiRepositoryImpl implements VehicleRepository.IDK{

    private VehicleApi mVehicleApi;
    private VehicleRepository.VehiclesCallback mVehiclesCallback;
    private VehicleRepository.ServicesCallback mServicesCallback;
    private VehicleRepository.InsurancesCallback mInsurancesCallback;
    private VehicleRepository.RefuelingsCallback mRefuelingsCallback;

    public VehicleApiRepositoryImpl(VehicleApi mVehicleApi, VehicleRepository.VehiclesCallback mVehiclesCallback) {
        this.mVehicleApi = mVehicleApi;
        this.mVehiclesCallback = Preconditions.checkNotNull(mVehiclesCallback);
    }

    public VehicleApiRepositoryImpl(VehicleApi mVehicleApi, VehicleRepository.ServicesCallback mServicesCallback) {
        this.mVehicleApi = mVehicleApi;
        this.mServicesCallback = Preconditions.checkNotNull(mServicesCallback);
    }

    public VehicleApiRepositoryImpl(VehicleApi mVehicleApi, VehicleRepository.InsurancesCallback mInsurancesCallback) {
        this.mVehicleApi = mVehicleApi;
        this.mInsurancesCallback = Preconditions.checkNotNull(mInsurancesCallback);
    }

    public VehicleApiRepositoryImpl(VehicleApi mVehicleApi, VehicleRepository.RefuelingsCallback mRefuelingsCallback) {
        this.mVehicleApi = mVehicleApi;
        this.mRefuelingsCallback = Preconditions.checkNotNull(mRefuelingsCallback);
    }

    @Override
    public void saveVehicle(final Vehicle vehicle) {
        Call<Vehicle> vehicleCall = mVehicleApi.saveVehicle(vehicle);
        vehicleCall.enqueue(new Callback<Vehicle>() {
            @Override
            public void onResponse(Call<Vehicle> call, Response<Vehicle> response) {
                if (response.isSuccessful()) {
                    Vehicle responseBody = response.body();
                    mVehiclesCallback.onVehicleSaved(responseBody);
                }else {
                    mVehiclesCallback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Vehicle> call, Throwable t) {
                mVehiclesCallback.onFailure();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {

    }

    public void updateVehicle(String vehicleId, final Vehicle vehicle) {
        Call<Vehicle> vehicleCall = mVehicleApi.updateVehicle(vehicleId, vehicle);
        vehicleCall.enqueue(new Callback<Vehicle>() {
            @Override
            public void onResponse(Call<Vehicle> call, Response<Vehicle> response) {
                if (response.isSuccessful()) {
                    Vehicle responseBody = response.body();
                    mVehiclesCallback.onVehicleSaved(responseBody);
                }else {
                    mVehiclesCallback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Vehicle> call, Throwable t) {
                mVehiclesCallback.onFailure();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void deleteVehicle(String id) {
        //TODO: wait for Petyo's API
    }

    @Override
    public void getVehicleById(String id) {
        //TODO: it might be pointless
    }

    @Override
    public void getVehicles() {
        Call<List<Vehicle>> vehicles = mVehicleApi.getVehicles();
        vehicles.enqueue(new Callback<List<Vehicle>>() {
            @Override
            public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response) {
                if (response.isSuccessful()) {
                    List<Vehicle> responseBody = response.body();
                    mVehiclesCallback.onVehiclesAvailable(responseBody);
                }else {
                    mVehiclesCallback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<Vehicle>> call, Throwable t) {
                mVehiclesCallback.onFailure();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void saveRefueling(String vehicleId, Refueling refueling) {

    }

    @Override
    public void updateRefueling(String vehicleId, Refueling refueling) {

    }

    @Override
    public void saveService(String vehicleId, Service service) {

    }


    @Override
    public void updateService(String vehicleId, Service service) {

    }

    @Override
    public void saveInsurance(String vehicleId, Insurance insurance) {

    }

    @Override
    public void updateInsurance(String vehicleId, Insurance insurance) {

    }
}
