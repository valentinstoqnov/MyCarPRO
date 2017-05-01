package elsys.mycar.mycarpro.addedit.service;

import java.util.List;

import elsys.mycar.mycarpro.base.BasePresenter;
import elsys.mycar.mycarpro.base.BaseView;

public interface AddEditServiceContract {

    interface View extends BaseView<Presenter>{

        void showProgress();

        void hideProgress();

        void showMessage(String message);

        void setDate(String date);

        void setTime(String time);

        void setType(String serviceType);

        void setPrice(String price);

        void setOdometer(String odometer);

        void setNote(String note);

        void showServiceTypes(List<String> items);

        void exit();

        boolean isActive();
    }

    interface Presenter extends BasePresenter{

        void saveService(String serviceType, String price, String odometer, String date, String time, String note);

        void onDatePicked(int year, int month, int day);

        void onTimePicked(int hour, int minute);

        boolean isDataMissing();
    }
}
