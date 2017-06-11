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
    private VehicleRepositoryImpl mVehicleRepository;
    private boolean mIsDataMissing;

    public ActivitiesPresenter(String mVehicleId, ActivitiesContract.View mView, VehicleRepositoryImpl mVehicleRepository, boolean mIsDataMissing) {
        this.mVehicleId = mVehicleId;
        this.mView = Preconditions.checkNotNull(mView);
        this.mVehicleRepository = Preconditions.checkNotNull(mVehicleRepository);
        this.mIsDataMissing = mIsDataMissing;
    }

    @Override
    public void start() {
        //if (mIsDataMissing) {
            mVehicleRepository.fetchVehicleById(mVehicleId, this);
       // }
    }

    @Override
    public void onVehicleChanged(String vehicleId) {
        mVehicleRepository.fetchVehicleById(vehicleId, this);
    }

    @Override
    public void provideServices() {
        if (mVehicle != null) {
           // mView.showServices(mVehicle.getServices());
        }else {
            onFailure();
        }
    }

    @Override
    public void provideInsurances() {
        if (mVehicle != null) {
           // mView.showInsurances(mVehicle.getInsurances());
        }else {
            onFailure();
        }
    }

    @Override
    public void provideRefuelings() {
        if (mVehicle != null) {
          //  mView.showRefueling(mVehicle.getRefuelings());
        }else {
            onFailure();
        }
    }

    @Override
    public void onSuccess(Vehicle vehicle) {
        mVehicle = vehicle;
        mIsDataMissing = false;
        provideActivities();
    }

    @Override
    public void onFailure() {
        mIsDataMissing= true;
        mView.showMessage("Could't find such vehicle");
    }

    private void provideActivities() {
        if (mVehicle != null) {
            /*mView.showServices(mVehicle.getServices());
            mView.showInsurances(mVehicle.getInsurances());
            mView.showRefueling(mVehicle.getRefuelings());*/
        }else {
            onFailure();
        }
    }
}
