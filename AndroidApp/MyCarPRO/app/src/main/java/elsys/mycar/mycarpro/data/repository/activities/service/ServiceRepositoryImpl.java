package elsys.mycar.mycarpro.data.repository.activities.service;

import elsys.mycar.mycarpro.data.api.ServiceApi;
import elsys.mycar.mycarpro.data.repository.OnSaveOrUpdateCallback;
import elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepository;
import elsys.mycar.mycarpro.model.Service;
import elsys.mycar.mycarpro.model.Vehicle;
import elsys.mycar.mycarpro.util.ActivitiesRepositoryUtils;
import elsys.mycar.mycarpro.util.ProviderUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceRepositoryImpl implements ServiceRepository {

    private ServiceApi mServiceApi;
    private String mToken;

    public ServiceRepositoryImpl(ServiceApi serviceApi, String token) {
        mServiceApi = serviceApi;
        mToken = token;
    }

    @Override
    public void saveService(final String vehicleId, final Service service, final OnSaveOrUpdateCallback<Service> callback) {
        ProviderUtils.getVehicleRepository(mToken)
                .getVehicleById(vehicleId, new VehicleRepository.OnVehicleFetchedCallback() {
                    @Override
                    public void onSuccess(Vehicle vehicle) {
                        Call<Service> call = mServiceApi.saveService(vehicleId, service);
                        call.enqueue(ActivitiesRepositoryUtils.provideSaveUpdateCallback4Retrofit(callback));
                    }

                    @Override
                    public void onFailure() {
                        callback.onFailure();
                    }
                });
    }

    @Override
    public void updateService(final String vehicleId, String serviceId, final Service service, final OnSaveOrUpdateCallback<Service> callback) {
        ProviderUtils.getVehicleRepository(mToken)
                .getVehicleById(vehicleId, new VehicleRepository.OnVehicleFetchedCallback() {
                    @Override
                    public void onSuccess(Vehicle vehicle) {
                        Call<Service> call = mServiceApi.updateService(vehicleId, service);
                        call.enqueue(ActivitiesRepositoryUtils.provideSaveUpdateCallback4Retrofit(callback));
                    }

                    @Override
                    public void onFailure() {
                        callback.onFailure();
                    }
                });
    }

    @Override
    public void getServiceById(String vehicleId, final String serviceId, final OnServiceFetchedCallback callback) {
        ProviderUtils.getVehicleRepository(mToken)
                .getVehicleById(vehicleId, new VehicleRepository.OnVehicleFetchedCallback() {
                    @Override
                    public void onSuccess(Vehicle vehicle) {
                        for (Service service : vehicle.getServices()) {
                            if (serviceId.equals(service.getId())) {
                                callback.onSuccess(service);
                                return;
                            }
                        }
                        callback.onFailure();
                    }

                    @Override
                    public void onFailure() {
                        callback.onFailure();
                    }
                });
    }
}
