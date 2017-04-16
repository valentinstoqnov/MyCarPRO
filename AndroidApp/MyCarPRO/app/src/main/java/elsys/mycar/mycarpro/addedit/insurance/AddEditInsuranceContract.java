package elsys.mycar.mycarpro.addedit.insurance;

import elsys.mycar.mycarpro.base.BasePresenter;
import elsys.mycar.mycarpro.base.BaseView;

public interface AddEditInsuranceContract {

    interface View extends BaseView<Presenter>{

        void showProgress();

        void hideProgress();

        void showMessage(String message);

        void setCompanyName(String companyName);

        void setPrice(String price);

        void setOdometer(String odometer);

        void setDate(String date);

        void setExpirationDate(String expirationDate);

        void setNote(String note);

        void exit();
    }

    interface Presenter extends BasePresenter {

        void saveInsurance(String companyName, String price, String odometer, String date, String expirationDate, String note);

        void onDatePicked(int year, int month, int day, boolean isExpiration);

        boolean isDataMissing();
    }
}
