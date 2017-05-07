package elsys.mycar.mycarpro.detail;

import android.graphics.Bitmap;

import elsys.mycar.mycarpro.base.BasePresenter;
import elsys.mycar.mycarpro.base.BaseView;

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

        void setNote(String note);
    }

    interface Presenter extends BasePresenter {

        boolean isDataMissing();
    }
}
