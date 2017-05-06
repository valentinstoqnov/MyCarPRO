package elsys.mycar.mycarpro.homescreen;

import java.util.List;

import elsys.mycar.mycarpro.base.BasePresenter;
import elsys.mycar.mycarpro.base.BaseView;

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void showMessage(String message);

        void setSelectedVehicleId(String vehicleId);

        void showVehicleNames(List<String> items);

        void showAddEditVehicleUi();

        void showAddEditServiceUi(String vehicleId);

        void showAddEditInsuranceUi(String vehicleId);

        void showAddEditRefuelingUi(String vehicleId);

        void showVehiclesUi();

        void showActivitiesUi();

        void showStatisticsUi();

        void showProfileUi();
    }

    interface Presenter extends BasePresenter {

        void openAddEditVehicle();

        void openAddEditService();

        void openAddEditInsurance();

        void openAddEditRefueling();

        void openVehicles();

        void openActivities();

        void openStatistics();

        void openProfile();

        void onSelectedVehicleChanged(int position);
    }

    interface HomewView extends BaseView<HomePresenter> {

        void showMessage(String message);

        void showVehicleNames(List<String> items);

        void showAddEditVehicleUi();

        void showAddEditServiceUi(String vehicleId);

        void showAddEditInsuranceUi(String vehicleId);

        void showAddEditRefuelingUi(String vehicleId);

        void showVehiclesUi();

        void showActivitiesUi();

        void showStatisticsUi();

        void showProfileUi();
    }

    interface HomePresenter extends BasePresenter {

        void openAddEditVehicle();

        void openAddEditService(int position);

        void openAddEditInsurance(int position);

        void openAddEditRefueling(int position);

        void openVehicles();

        void openActivities();

        void openStatistics();

        void openProfile();

        void setDataMissing();
    }
}
