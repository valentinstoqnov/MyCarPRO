package vstoyanov.mycar.mycarpro.data.repository.activities.service;

import vstoyanov.mycar.mycarpro.data.model.Service;
import vstoyanov.mycar.mycarpro.data.repository.OnItemFetchedCallback;
import vstoyanov.mycar.mycarpro.data.repository.OnItemsFetchedCallback;
import vstoyanov.mycar.mycarpro.data.repository.OnSaveUpdateDeleteCallback;

public interface ServiceRepository {

    void saveService(Service service, OnSaveUpdateDeleteCallback callback);

    void updateService(String serviceId, Service service, OnSaveUpdateDeleteCallback callback);

    void deleteService(String serviceId, OnSaveUpdateDeleteCallback callback);

    void fetchServiceById(String serviceId, OnItemFetchedCallback<Service> callback);

    void fetchServices(String vehicleId, OnItemsFetchedCallback<Service> callback);

}
