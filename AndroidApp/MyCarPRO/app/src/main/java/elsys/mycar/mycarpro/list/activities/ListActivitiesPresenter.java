package elsys.mycar.mycarpro.list.activities;

import elsys.mycar.mycarpro.data.VehicleRepository;

public class ListActivitiesPresenter implements ListActivitiesContract.Presenter {

    private String mVehicleId;
    private VehicleRepository mVehicleRepository;
    private ListActivitiesContract.View mView;
    private boolean mIsDataMissing;

    @Override
    public void start() {
        if (mVehicleId == null) {
            mView.showNoSuchVehicle();
        }else if (mIsDataMissing) {
            //fetch data...
        }
    }

    @Override
    public boolean isDataMissing() {
        return false;
    }
}
