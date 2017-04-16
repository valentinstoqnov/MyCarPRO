package elsys.mycar.mycarpro.list.vehicles;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import elsys.mycar.mycarpro.BasePresenter;
import elsys.mycar.mycarpro.BaseView;
import elsys.mycar.mycarpro.model.Vehicle;

public interface ListVehicleContract {

    interface View extends BaseView<Presenter>{

        void addVehicles(List<Vehicle> vehicles);
    }

    interface Presenter extends BasePresenter {

        void deleteVehicle(String vehicleId);
    }

    interface Adapter {

        void addVehicles(List<Vehicle> vehicles);

        void addVehicle(Vehicle vehicle);

        void removeVehicle(int position);
    }
}
