package elsys.mycar.mycarpro.addedit.refueling;

import elsys.mycar.mycarpro.BasePresenter;
import elsys.mycar.mycarpro.BaseView;

public interface AddEditRefuelingContract {

    interface View extends BaseView<Presenter>{

        void showProgress();

        void hideProgress();

        void showMessage(String message);

        void setDate(String date);

        void setTime(String time);

        void setCompanyName(String companyName);

        void setQuantity(String quantity);

        void setPrice(String price);

        void setOdometer(String odometer);

        void setNote(String note);

        void setFullFuelTank(boolean isFull);
    }

    interface Presenter extends BasePresenter {

        void saveRefueling(String companyName, String quantity, String price, String odometer, String note, boolean isFull);
    }
}
