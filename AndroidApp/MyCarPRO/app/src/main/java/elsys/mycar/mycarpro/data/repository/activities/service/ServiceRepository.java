package elsys.mycar.mycarpro.data.repository.activities.service;

import elsys.mycar.mycarpro.data.repository.OnSaveOrUpdateCallback;
import elsys.mycar.mycarpro.model.Service;

public interface ServiceRepository {

    void saveService(String vehicleId, Service service, OnSaveOrUpdateCallback<Service> callback);

    void updateService(String vehicleId, String serviceId, Service service, OnSaveOrUpdateCallback<Service> callback);

    void getServiceById(String vehicleId, String serviceId, OnServiceFetchedCallback callback);

    interface OnServiceFetchedCallback {

        void onSuccess(Service service);

        void onFailure();
    }
}
