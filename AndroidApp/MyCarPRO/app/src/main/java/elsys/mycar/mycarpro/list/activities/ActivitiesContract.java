package elsys.mycar.mycarpro.list.activities;

import elsys.mycar.mycarpro.base.BasePresenter;
import elsys.mycar.mycarpro.base.BaseView;

public interface ActivitiesContract {

    interface View extends BaseView<Presenter> {

        void showVehicleContent(String vehicleId);
    }

    interface Presenter extends BasePresenter {

        void onVehicleChange(String vehicleName);
    }
}
