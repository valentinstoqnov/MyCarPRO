package elsys.mycar.mycarpro.list.vehicles;

import com.google.common.base.Preconditions;

import java.util.List;

import elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepository;
import elsys.mycar.mycarpro.data.model.Vehicle;

public class ListVehiclePresenter implements ListVehicleContract.Presenter, VehicleRepository.OnVehiclesFetchedCallback, VehicleRepository.OnDeleteCallback {

    private VehicleRepository mVehicleRepository;
    private ListVehicleContract.View mView;
    private boolean mIsDataMissing = true;

    public ListVehiclePresenter(VehicleRepository mVehicleRepository, ListVehicleContract.View mView) {
        this.mVehicleRepository = Preconditions.checkNotNull(mVehicleRepository);
        this.mView = Preconditions.checkNotNull(mView);
    }

    @Override
    public void start() {
        if (mIsDataMissing && mView.isActive()) {
            mVehicleRepository.getVehicles(this);
        }
    }

    /*@Override
    public void deleteVehicle(String vehicleId) {
        mVehicleRepository.deleteVehicle(vehicleId, this);
    }*/

    @Override
    public void onSuccess(List<Vehicle> vehicles) {
        mView.showVehicles(vehicles);
        mIsDataMissing = false;
    }

    @Override
    public void onSuccess() {
        mView.showMessage("Vehicle successfully deleted!");
    }

    @Override
    public void onFailure() {
        mView.showMessage("Failed to do this action, please try again");
    }

    @Override
    public void openVehicleDetails(Vehicle vehicle) {
        vehicle = Preconditions.checkNotNull(vehicle, "Vehicle cannot be null");
        mView.showDetailVehicleUi(vehicle.getId());
    }
}
