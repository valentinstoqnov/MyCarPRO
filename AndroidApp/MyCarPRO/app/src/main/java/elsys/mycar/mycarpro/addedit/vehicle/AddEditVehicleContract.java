package elsys.mycar.mycarpro.addedit.vehicle;

import java.text.ParseException;

import elsys.mycar.mycarpro.BasePresenter;
import elsys.mycar.mycarpro.BaseView;

public interface AddEditVehicleContract {

    interface View extends BaseView<Presenter> {

        void showProgress();

        void hideProgress();

        void showMessage(String message);

        void exit();

        void setName(String name);

        void setMake(String make);

        void setModel(String model);

        void setManufactureDate(String date);

        void setPhoto(/*Drawable or Bitmap*/);

        void setFuelTank(/*some variables here*/);
    }

    interface Presenter extends BasePresenter {

        void onDatePicked(int year, int month, int day);

        void saveVehicle(String name, String make, String model, String manufactureDate, String odometer, String horsePower, String notes/*photo and fuel tanks*/);
    }
}
