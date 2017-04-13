package elsys.mycar.mycarpro.detail;

import android.graphics.Bitmap;

import elsys.mycar.mycarpro.BasePresenter;
import elsys.mycar.mycarpro.BaseView;

public interface DetailVehicleContract {

    interface View extends BaseView<Presenter> {

        void setPhoto(Bitmap bitmap);

        void setName(String name);

        void setMake(String make);

        void setModel(String model);

        void setManufactureDate(String date);

        void setHorsePower(String horsePower);

        void setFuelType(String fuelType);

        void setFuelTankCapacity(String capacity);

        void setFuelConsumption(String consumption);

        void setNote(String note);
    }

    interface Presenter extends BasePresenter {


    }
}
