package elsys.mycar.mycarpro.data.repository.vehicle;

import java.util.List;

import elsys.mycar.mycarpro.data.repository.OnSaveOrUpdateCallback;
import elsys.mycar.mycarpro.data.model.Vehicle;

public interface VehicleRepository {

    void saveVehicle(Vehicle vehicle, OnSaveOrUpdateCallback<Vehicle> callback);

    void updateVehicle(String vehicleId, Vehicle vehicle, OnSaveOrUpdateCallback<Vehicle> callback);

    void deleteVehicle(String id, OnDeleteCallback callback);

    void getVehicleById(String id, OnVehicleFetchedCallback callback);

    void getVehicles(OnVehiclesFetchedCallback callback);


    interface OnDeleteCallback {

        void onSuccess();

        void onFailure();
    }

    interface OnVehicleFetchedCallback {

        void onSuccess(Vehicle vehicle);

        void onFailure();
    }

    interface OnVehiclesFetchedCallback {

        void onSuccess(List<Vehicle> vehicles);

        void onFailure();
    }

    interface Callback<T> {

        void onItemSavedOrUpdated(T item);

        void onItemsFetched(List<T> items);

        void onFailure();
/*
        void onVehicleSavedOrChanged(Vehicle vehicle);

        void onVehiclesFetched(List<Vehicle> vehicles);

        void onFailure();*/
    }
}
