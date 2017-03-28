package mycar.elsys.addedit.vehicle;

import mycar.elsys.base.presenter.BasePresenter;
import mycar.elsys.realm.models.Vehicle;

public class AddEditVehiclePresenter extends BasePresenter<AddEditVehicleContract.View> implements AddEditVehicleContract.Presenter, AddEditVehicleContract.Interactor.OnSaveVehicleListener{

    private AddEditVehicleInteractor mInteractor;

    public AddEditVehiclePresenter() {
        this.mInteractor = new AddEditVehicleInteractor();
    }

    @Override
    public void saveVehicle() {
        mInteractor.validateVehicle(null, this);
    }

    @Override
    public void populateVehicle() {
        Vehicle vehicle = mInteractor.getVehicleById(null);
    }

    @Override
    public void onValidationSuccessful(Vehicle vehicle) {
        mInteractor.saveVehicle(vehicle);
    }

    @Override
    public void onNameError() {
        mView.setNameError();

    }

    @Override
    public void onMakeError() {
        mView.setMakeError();
    }

    @Override
    public void onModelError() {
        mView.setModelError();
    }

    @Override
    public void onTypeError() {
        mView.setTypeError();
    }

    @Override
    public void onHorsePowerError() {
        mView.setHorsePowerError();
    }

    @Override
    public void onRegistrationPlateError() {
        mView.setRegistrationPlateError();
    }

    @Override
    public void onInfoError() {
        mView.setInfoError();
    }

    @Override
    public void onFuelTankError() {
        mView.setFuelTankError();
    }
}
