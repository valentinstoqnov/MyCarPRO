package elsys.mycar.mycarpro.list.vehicles;

import com.google.common.base.Preconditions;

import java.util.List;

import elsys.mycar.mycarpro.data.model.User;
import elsys.mycar.mycarpro.data.model.Vehicle;
import elsys.mycar.mycarpro.data.repository.OnItemsFetchedCallback;
import elsys.mycar.mycarpro.data.repository.OnSaveUpdateDeleteCallback;
import elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepository;

public class ListVehiclePresenter implements ListVehicleContract.Presenter, OnItemsFetchedCallback<Vehicle>, OnSaveUpdateDeleteCallback {

    private VehicleRepository mVehicleRepository;
    private ListVehicleContract.View mView;
    private String mUserId;
    private boolean mIsDataMissing = true;

    public ListVehiclePresenter(String userId, VehicleRepository mVehicleRepository, ListVehicleContract.View mView) {
        this.mVehicleRepository = Preconditions.checkNotNull(mVehicleRepository);
        this.mView = Preconditions.checkNotNull(mView);
        this.mUserId = userId;
    }

    @Override
    public void start() {
        //if (mIsDataMissing && mView.isActive()) {
          //  mVehicleRepository.fetchVehicles(Preconditions.checkNotNull(mUserId, "userId cannot be null"), this);
        //}
    }

    @Override
    public void onSuccess(String name) {
        mView.showMessage(name + " vehicle successfully deleted!");
    }

    @Override
    public void onSuccess(List<Vehicle> items) {
        mView.showVehicles(items);
        mIsDataMissing = false;
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

    /*@Override
    public void deleteVehicle(String vehicleId) {
        mVehicleRepository.deleteVehicle(vehicleId, this);
    }*/

   /* @Override
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
    }*/
}
