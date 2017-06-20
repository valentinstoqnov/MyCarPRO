package elsys.mycar.mycarpro.list.vehicles;

import java.util.List;

import elsys.mycar.mycarpro.base.BasePresenter;
import elsys.mycar.mycarpro.base.BaseView;
import elsys.mycar.mycarpro.data.model.Vehicle;

public interface ListVehicleContract {

    interface View extends BaseView<Presenter>{

        void showVehicles(List<Vehicle> vehicles);

        void showNoVehiclesFound();

        void showProgress();

        void hideProgress();

        void showVehiclesFetchError();

        void showDetailVehicleUi(String vehicleId);

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void openVehicleDetails(Vehicle vehicle);

        boolean isDataMissing();
    }

    interface Adapter {

        void addVehicles(List<Vehicle> vehicles);

        void addVehicle(Vehicle vehicle);

        void removeVehicle(int position);
    }
}
