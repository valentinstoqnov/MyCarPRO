package elsys.mycar.mycarpro.data.repository.activities.service;

import elsys.mycar.mycarpro.data.model.Service;
import elsys.mycar.mycarpro.data.repository.OnSaveUpdateDeleteCallback;

public interface ServiceRepository {

    void saveService(String vehicleId, Service service, OnSaveUpdateDeleteCallback callback);

    void updateService(String vehicleId, String serviceId, Service service, OnSaveUpdateDeleteCallback callback);

    void getServiceById(String vehicleId, String serviceId, OnServiceFetchedCallback callback);

    interface OnServiceFetchedCallback {

        void onSuccess(Service service);

        void onFailure();
    }
}
