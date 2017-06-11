package elsys.mycar.mycarpro.homescreen;

import java.util.HashMap;

import elsys.mycar.mycarpro.base.BasePresenter;
import elsys.mycar.mycarpro.base.BaseView;

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void showMessage(String message);

        void showVehicleItemsInDropdown(HashMap<String, String> items);

        void showAddEditVehicleUi();

        void showAddEditServiceUi(String vehicleId);

        void showAddEditInsuranceUi(String vehicleId);

        void showAddEditRefuelingUi(String vehicleId);

        void showVehiclesUi();

        void showActivitiesUi();

        void showStatisticsUi();

        void showProfileUi();

        String getCurrentUserId();
    }

    interface Presenter extends BasePresenter {

        void openAddEditVehicle();

        void openAddEditService(String vehicleId);

        void openAddEditInsurance(String vehicleId);

        void openAddEditRefueling(String vehicleId);

        void openVehicles();

        void openActivities();

        void openStatistics();

        void openProfile();

        void setDataMissing();
    }
}
