package vstoyanov.mycar.mycarpro.data.repository.vehicle;

import vstoyanov.mycar.mycarpro.data.model.Vehicle;
import vstoyanov.mycar.mycarpro.data.repository.OnItemFetchedCallback;
import vstoyanov.mycar.mycarpro.data.repository.OnItemsFetchedCallback;
import vstoyanov.mycar.mycarpro.data.repository.OnSaveUpdateDeleteCallback;

public interface VehicleRepository {

    void saveVehicle(Vehicle vehicle, OnSaveUpdateDeleteCallback callback);

    void updateVehicle(String vehicleId, Vehicle vehicle, OnSaveUpdateDeleteCallback callback);

    void deleteVehicle(String vehicleId, OnSaveUpdateDeleteCallback callback);

    void fetchVehicleById(String id, OnItemFetchedCallback<Vehicle> callback);

    void fetchVehicles(String userId, OnItemsFetchedCallback<Vehicle> callback);
}
