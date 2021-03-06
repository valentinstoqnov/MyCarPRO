package vstoyanov.mycar.mycarpro.addedit.insurance;

import java.util.List;

import vstoyanov.mycar.mycarpro.base.BasePresenter;
import vstoyanov.mycar.mycarpro.base.BaseView;

public interface AddEditInsuranceContract {

    interface View extends BaseView<Presenter>{

        void showProgress();

        void hideProgress();

        void showNoSuchVehicle();

        void showInsuranceRetrievalError();

        void showDateError();

        void showEmptyFieldsError();

        void showPriceOrOdometerParseError();

        void showInsuranceSuccessfullySaved(String name);

        void showInsuranceSaveError();

        void setCompanyName(String companyName);

        void setPrice(String price);

        void setOdometer(String odometer);

        void setDate(String date);

        void setExpirationDate(String expirationDate);

        void setNote(String note);

        void addCompanies(List<String> items);

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void saveInsurance(String companyName, String price, String odometer, String date, String expirationDate, String note);

        void onDatePicked(int year, int month, int day, boolean isExpiration);

        boolean isDataMissing();
    }
}
