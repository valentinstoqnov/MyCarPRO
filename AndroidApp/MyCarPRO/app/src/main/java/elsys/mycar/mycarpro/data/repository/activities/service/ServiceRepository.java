package elsys.mycar.mycarpro.data.repository.activities.service;

import elsys.mycar.mycarpro.data.model.Service;
import elsys.mycar.mycarpro.data.repository.OnItemFetchedCallback;
import elsys.mycar.mycarpro.data.repository.OnItemsFetchedCallback;
import elsys.mycar.mycarpro.data.repository.OnSaveUpdateDeleteCallback;

public interface ServiceRepository {

    void saveService(Service service, OnSaveUpdateDeleteCallback callback);

    void updateService(String serviceId, Service service, OnSaveUpdateDeleteCallback callback);

    void deleteService(String serviceId, OnSaveUpdateDeleteCallback callback);

    void fetchServiceById(String serviceId, OnItemFetchedCallback<Service> callback);

    void fetchServices(String vehicleId, OnItemsFetchedCallback<Service> callback);

}
