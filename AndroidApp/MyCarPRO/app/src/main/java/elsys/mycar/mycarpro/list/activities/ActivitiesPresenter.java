package elsys.mycar.mycarpro.list.activities;

import elsys.mycar.mycarpro.data.VehicleRepository;

public class ActivitiesPresenter implements ActivitiesContract.Presenter{

    private VehicleRepository mVehicleRepository;
    private ActivitiesContract.View mView;
    private String mVehicleName;

    public ActivitiesPresenter(VehicleRepository mVehicleRepository, ActivitiesContract.View mView, String mVehicleName) {
        this.mVehicleRepository = mVehicleRepository;
        this.mView = mView;
        this.mVehicleName = mVehicleName;
    }

    @Override
    public void start() {
        String id = mVehicleRepository.getVehicleIdByName(mVehicleName);
        mView.showVehicleContent(id);
    }

    @Override
    public void onVehicleChange(String vehicleName) {
        mVehicleName = vehicleName;
        start();
    }
}
