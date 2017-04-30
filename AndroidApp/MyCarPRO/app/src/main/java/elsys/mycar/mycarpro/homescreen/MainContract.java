package elsys.mycar.mycarpro.homescreen;

import java.util.List;

import elsys.mycar.mycarpro.base.BasePresenter;
import elsys.mycar.mycarpro.base.BaseView;

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void setSelectedVehicleId(String vehicleId);

        void setVehicleNames(List<String> items);

        void showAddEditVehicleUi();

        void showAddEditServiceUi(String vehicleId);

        void showAddEditInsuranceUi(String vehicleId);

        void showAddEditRefuelingUi(String vehicleId);
    }

    interface Presenter extends BasePresenter {

        void openAddEditVehicle();

        void openAddEditService();

        void openAddEditInsurance();

        void openAddEditRefueling();

        void onSelectedVehicleChanged(String vehicleName);
    }
}
