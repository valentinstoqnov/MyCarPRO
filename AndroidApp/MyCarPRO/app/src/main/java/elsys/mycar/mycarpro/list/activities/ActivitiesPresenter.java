package elsys.mycar.mycarpro.list.activities;

import com.google.common.base.Preconditions;

import elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepository;
import elsys.mycar.mycarpro.data.model.Vehicle;

public class ActivitiesPresenter implements ActivitiesContract.Presenter{

    private Vehicle mVehicle;
    private VehicleRepository mVehicleRepository;
    private ActivitiesContract.View mView;
    private boolean mIsDataMissing = true;

    public ActivitiesPresenter(VehicleRepository mVehicleRepository, ActivitiesContract.View mView) {
        this.mVehicleRepository = Preconditions.checkNotNull(mVehicleRepository);
        this.mView = Preconditions.checkNotNull(mView);
    }

    @Override
    public void start() {
        if (mIsDataMissing) {

        }
    }

    @Override
    public void onVehicleChange(String vehicleName) {
        /*mVehicleName = vehicleName;
        start();*/
    }
}
