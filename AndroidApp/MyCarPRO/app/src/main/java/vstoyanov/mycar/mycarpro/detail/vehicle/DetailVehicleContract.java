package vstoyanov.mycar.mycarpro.detail.vehicle;

import vstoyanov.mycar.mycarpro.base.BasePresenter;
import vstoyanov.mycar.mycarpro.base.BaseView;

public interface DetailVehicleContract {

    interface View extends BaseView<Presenter> {

        void showNoSuchVehicleError();

        void showMessage(String message);

        void setColor(int color);

        void setName(String name);

        void setMake(String make);

        void setModel(String model);

        void setManufactureDate(String date);

        void setHorsePower(String horsePower);

        void setFuelTank(String fuelTank);

        void setOdometer(String odometer);

        void setNote(String note);

        void showEditVehicleUi(String id);
    }

    interface Presenter extends BasePresenter {

        void openEditVehicleUi();

        boolean isDataMissing();
    }
}
