package elsys.mycar.mycarpro.list.activities;

import com.google.common.base.Preconditions;

import elsys.mycar.mycarpro.data.model.Vehicle;
import elsys.mycar.mycarpro.data.repository.OnItemFetchedCallback;
import elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepository;
import elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepositoryImpl;

public class ActivitiesPresenter implements ActivitiesContract.Presenter, OnItemFetchedCallback<Vehicle> {

    private Vehicle mVehicle;
    private String mVehicleId;
    private ActivitiesContract.View mView;
    private boolean mIsDataMissing;

    public ActivitiesPresenter(String vehicleId, ActivitiesContract.View view, VehicleRepositoryImpl vehicleRepository, boolean isDataMissing) {
        this.mVehicleId = vehicleId;
        this.mView = Preconditions.checkNotNull(view, "view cannot be null");
        this.mIsDataMissing = isDataMissing;
    }

    @Override
    public void start() {

    }

    @Override
    public void onSuccess(Vehicle item) {

    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onVehicleChanged(String vehicleId) {

    }

    @Override
    public void provideServices() {

    }

    @Override
    public void provideInsurances() {

    }

    @Override
    public void provideRefuelings() {

    }
}
