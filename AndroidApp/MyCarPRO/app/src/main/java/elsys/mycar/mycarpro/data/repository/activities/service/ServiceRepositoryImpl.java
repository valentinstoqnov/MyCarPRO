package elsys.mycar.mycarpro.data.repository.activities.service;

import elsys.mycar.mycarpro.data.api.ServiceApi;
import elsys.mycar.mycarpro.data.model.Service;
import elsys.mycar.mycarpro.data.model.Vehicle;
import elsys.mycar.mycarpro.data.repository.OnSaveUpdateDeleteCallback;
import elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepository;
import retrofit2.Call;

public class ServiceRepositoryImpl implements ServiceRepository {

    @Override
    public void saveService(String vehicleId, Service service, OnSaveUpdateDeleteCallback callback) {

    }

    @Override
    public void updateService(String vehicleId, String serviceId, Service service, OnSaveUpdateDeleteCallback callback) {

    }

    @Override
    public void getServiceById(String vehicleId, String serviceId, OnServiceFetchedCallback callback) {

    }
}