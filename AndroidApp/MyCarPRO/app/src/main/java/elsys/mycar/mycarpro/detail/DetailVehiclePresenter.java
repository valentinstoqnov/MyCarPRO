package elsys.mycar.mycarpro.detail;

import com.google.common.base.Preconditions;

import elsys.mycar.mycarpro.data.model.Vehicle;
import elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepository;

public class DetailVehiclePresenter implements DetailVehicleContract.Presenter {

    private DetailVehicleContract.View mView;
    private VehicleRepository mVehicleRepository;
    private String mVehicleId;
    private boolean mIsDataMissing;

    public DetailVehiclePresenter(DetailVehicleContract.View mView, VehicleRepository mVehicleRepository, String mVehicleId, boolean mIsDataMissing) {
        this.mView = Preconditions.checkNotNull(mView);
        this.mVehicleRepository = Preconditions.checkNotNull(mVehicleRepository);
        this.mVehicleId = Preconditions.checkNotNull(mVehicleId);
        this.mIsDataMissing = mIsDataMissing;
    }

    @Override
    public void start() {
        if (isDataMissing()) {
            populateVehicle();
        }
    }

    private void populateVehicle() {
        //Vehicle vehicle = mVehicleRepository.getById(mVehicleId);
    /*    if (vehicle != null) {
            mView.setMake(vehicle.getMake());
            mView.setModel(vehicle.getModel());
            mView.setManufactureDate(vehicle.getManufactureDate());
            mView.setHorsePower(String.valueOf(vehicle.getHorsePower()));
            mView.setNote(vehicle.getNote());
            mView.setName(vehicle.getName());
        }else {
            mView.showNoSuchVehicleError();
        }*/
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }
}
