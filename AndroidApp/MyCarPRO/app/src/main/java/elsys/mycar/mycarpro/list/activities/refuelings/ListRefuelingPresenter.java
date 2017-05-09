package elsys.mycar.mycarpro.list.activities.refuelings;

import com.google.common.base.Preconditions;

import java.util.List;

import elsys.mycar.mycarpro.data.model.Refueling;
import elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepository;

public class ListRefuelingPresenter implements ListRefuelingsContract.Presenter{

    private String mVehicleId;
    private VehicleRepository mVehicleRepository;
    private ListRefuelingsContract.View mView;
    private boolean mIsDataMissing;

    public ListRefuelingPresenter(String mVehicleId, VehicleRepository mVehicleRepository, ListRefuelingsContract.View mView, boolean mIsDataMissing) {
        this.mVehicleId = mVehicleId;
        this.mVehicleRepository = Preconditions.checkNotNull(mVehicleRepository);
        this.mView = Preconditions.checkNotNull(mView);
        this.mIsDataMissing = mIsDataMissing;
    }


    @Override
    public void start() {
        if (mIsDataMissing && mView.isActive()) {
           // loadItems();
        }
    }
/*
    @Override
    public void loadItems() {
        if (mVehicleId == null) {
          //  mView.showNoSuchVehicle();
        }else {
            mView.showProgress();
            mVehicleRepository.getVehicleById(mVehicleId, new VehicleRepository.OnVehicleFetchedCallback() {
                @Override
                public void onSuccess(Vehicle vehicle) {
                    processRefuelings(vehicle.getRefuelings());
                }

                @Override
                public void onFailure() {
                    mView.showMessage("Something went wrong...");
                    mView.hideProgress();
                }
            });
        }
    }

    @Override
    public void onVehicleChanged(String vehicleId) {
        mVehicleId = vehicleId;
        mIsDataMissing = true;
    }*/

    @Override
    public void openRefuelingDetails(Refueling refueling) {
        mView.showDetailItemUi(Preconditions.checkNotNull(refueling).getId());
    }

    @Override
    public void openItemDetails(Object item) {

    }

    @Override
    public void swapDataSet(List items) {

    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }

    private void processRefuelings(List<Refueling> refuelings) {
        if (refuelings == null || refuelings.isEmpty()) {
            mView.showNoItemsFound();
        }else {
            mView.showRefuelings(refuelings);
            mIsDataMissing = false;
        }
        mView.hideProgress();
    }
}
