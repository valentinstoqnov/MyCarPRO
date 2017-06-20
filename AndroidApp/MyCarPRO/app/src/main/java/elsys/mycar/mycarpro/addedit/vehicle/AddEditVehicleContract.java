package elsys.mycar.mycarpro.addedit.vehicle;

import java.util.List;

import elsys.mycar.mycarpro.base.BasePresenter;
import elsys.mycar.mycarpro.base.BaseView;

public interface AddEditVehicleContract {

    interface View extends BaseView<Presenter> {

        void showProgress();

        void hideProgress();

        void showNoSuchVehicle();

        void showDateError();

        void showEmptyFieldsError();

        void showPriceOrOdometerParseError();

        void showVehicleSuccessfullySaved(String name);

        void showVehicleSaveError();

        void setName(String name);

        void setMake(String make);

        void setModel(String model);

        void setManufactureDate(String date);

        void setDate(String date);

        void showMakes(List<String> items);

        void setColor(int color);

        void setOdometer(String odometer);

        void setHorsePower(String horsePower);

        void setNote(String note);

        void setFuelTank(String fuelTank);

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void onDatePicked(int year, int month, int day);

        void onFuelTankPicked(String fuelType, int fuelTankCapacity, double fuelConsumption);

        void saveVehicle(String name, String make, String model, String manufactureDate, String horsePower, String odometer, int color, String note);

        boolean isDataMissing();
    }
}
