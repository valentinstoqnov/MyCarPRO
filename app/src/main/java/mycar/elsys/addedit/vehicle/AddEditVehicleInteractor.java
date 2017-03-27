package mycar.elsys.addedit.vehicle;

import mycar.elsys.realm.models.Vehicle;

public class AddEditVehicleInteractor implements AddEditVehicleContract.Interactor{

    @Override
    public void validateVehicle(Vehicle vehicle, OnSaveVehicleListener listener) {
    }

    @Override
    public Vehicle getVehicleById(String id) {

        return null;
    }

    @Override
    public void saveVehicle(Vehicle vehicle) {

    }
}
