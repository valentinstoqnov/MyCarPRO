package elsys.mycar.mycarpro.list.activities.refuelings;

import android.util.Log;

import com.google.common.base.Preconditions;

import java.util.List;

import elsys.mycar.mycarpro.data.VehicleRepository;
import elsys.mycar.mycarpro.list.activities.ListActivitiesContract;
import elsys.mycar.mycarpro.model.Insurance;
import elsys.mycar.mycarpro.model.Refueling;

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
        if (mIsDataMissing) {
            loadItems();
        }
    }

    @Override
    public void loadItems() {
        if (mVehicleId == null) {
            mView.showNoSuchVehicle();
        }else {
            List<Refueling> refuelings = mVehicleRepository.getRefuelingsByVehicleId(mVehicleId);
            processRefuelings(refuelings);
        }
    }

    @Override
    public void onVehicleChanged(String vehicleId) {
        mVehicleId = vehicleId;
        loadItems();
    }

    @Override
    public void openRefuelingDetails(Refueling refueling) {
        mView.showDetailItemUi(Preconditions.checkNotNull(refueling).getId());
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
        }
    }
}
