package elsys.mycar.mycarpro.addedit.refueling;

import java.util.List;

import elsys.mycar.mycarpro.base.BasePresenter;
import elsys.mycar.mycarpro.base.BaseView;

public interface AddEditRefuelingContract {

    interface View extends BaseView<Presenter>{

        void showProgress();

        void hideProgress();

        void showNoSuchVehicle();

        void showRefuelingRetrievalError();

        void showDateError();

        void showRefuelingSaveError();

        void showPriceOrOdometerParseError();

        void showEmptyFieldsError();

        void showRefuelingSuccessfullySaved(String name);

        void setDate(String date);

        void setTime(String time);

        void setCompanyName(String companyName);

        void setQuantity(String quantity);

        void setPrice(String price);

        void setOdometer(String odometer);

        void setNote(String note);

        void setFullFuelTank(boolean isFull);

        void addGasStations(List<String> items);

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void saveRefueling(String companyName, String quantity, String price, String odometer, String date, String time, String note, boolean isFull);

        void onDatePicked(int year, int month, int day);

        void onTimePicked(int hour, int minute);

        boolean isDataMissing();
    }
}
