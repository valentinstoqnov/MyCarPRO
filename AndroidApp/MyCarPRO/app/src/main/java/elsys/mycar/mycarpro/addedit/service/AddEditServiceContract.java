package elsys.mycar.mycarpro.addedit.service;

import elsys.mycar.mycarpro.BasePresenter;
import elsys.mycar.mycarpro.BaseView;

public interface AddEditServiceContract {

    interface View extends BaseView<Presenter>{

        void showProgress();

        void hideProgress();

        void showMessage(String message);

        void setDate(String date);

        void setTime(String time);

        void setServiceType(String serviceType);

        void setPrice(String price);

        void setOdometer(String odometer);

        void setNote(String note);
    }

    interface Presenter extends BasePresenter{

        void saveService(String serviceType, String price, String odometer, String date, String time, String note);
    }
}
