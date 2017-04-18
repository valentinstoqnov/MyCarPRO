package elsys.mycar.mycarpro.list.activities.refuelings;

import com.google.common.base.Preconditions;

import java.util.List;

import elsys.mycar.mycarpro.data.VehicleRepository;
import elsys.mycar.mycarpro.list.activities.ListActivitiesContract;
import elsys.mycar.mycarpro.model.Refueling;

public class ListRefuelingPresenter implements ListActivitiesContract.Presenter{

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
        if (mVehicleId == null) {
            mView.showNoSuchVehicle();
        }else if (mIsDataMissing){
            List<Refueling> refuelings = mVehicleRepository.getRefuelingsByVehicleId(mVehicleId);
            if (refuelings == null || refuelings.size() == 0) {
                mView.showNoItemsFound();
            }else {
                mView.addItems(refuelings);
            }
        }
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }
}
