package mycar.elsys.addedit.vehicle;

import mycar.elsys.realm.models.Vehicle;
import mycar.elsys.base.view.BaseView;

public interface AddEditVehicleContract {

    interface View extends BaseView {

        void setName(String name);
        void setMake(String make);
        void setModel(String model);
        void setType(String type);
        void setHorsePower(String horsePower);
        void setRegistrationPlate(String registrationPlate);
        void setInfo(String info);

        String getName();
        String getMake();
        String getModel();
        String getType();
        String getHorsePower();
        String getRegistrationPlate();
        String getInfo();

        void setNameError();
        void setMakeError();
        void setModelError();
        void setTypeError();
        void setHorsePowerError();
        void setRegistrationPlateError();
        void setInfoError();
        void setFuelTankError();
        void navigateToHome();
    }

    interface Presenter {
        void saveVehicle();
        void populateVehicle();
    }

    interface Interactor {

        interface OnSaveVehicleListener{
            void onValidationSuccessful(Vehicle vehicle);
            void onNameError();
            void onMakeError();
            void onModelError();
            void onTypeError();
            void onHorsePowerError();
            void onRegistrationPlateError();
            void onInfoError();
            void onFuelTankError();
        }

        void validateVehicle(Vehicle vehicle, OnSaveVehicleListener listener);
        Vehicle getVehicleById(String id);
        void saveVehicle(Vehicle vehicle);
    }
}
