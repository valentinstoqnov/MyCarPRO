package elsys.mycar.mycarpro.list.vehicles;

import com.google.common.base.Preconditions;

import java.util.List;

import elsys.mycar.mycarpro.data.model.User;
import elsys.mycar.mycarpro.data.model.Vehicle;
import elsys.mycar.mycarpro.data.repository.OnItemsFetchedCallback;
import elsys.mycar.mycarpro.data.repository.OnSaveUpdateDeleteCallback;
import elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepository;

public class ListVehiclePresenter implements ListVehicleContract.Presenter {

    private VehicleRepository mVehicleRepository;
    private ListVehicleContract.View mView;
    private String mUserId;
    private boolean mIsDataMissing;

    public ListVehiclePresenter(String userId, VehicleRepository vehicleRepository, ListVehicleContract.View view, boolean isDataMissing) {
        this.mVehicleRepository = Preconditions.checkNotNull(vehicleRepository);
        this.mView = Preconditions.checkNotNull(view);
        this.mUserId = Preconditions.checkNotNull(userId);
        this.mIsDataMissing = isDataMissing;
    }

    @Override
    public void start() {
        if (mIsDataMissing) {
            mView.showProgress();
            mVehicleRepository.fetchVehicles(mUserId, new OnItemsFetchedCallback<Vehicle>() {
                @Override
                public void onSuccess(List<Vehicle> items) {
                    mView.showVehicles(items);
                    if (items.isEmpty()) {
                        mView.showNoVehiclesFound();
                    }
                    mView.hideProgress();
                }

                @Override
                public void onFailure() {
                    mView.hideProgress();
                    mView.showVehiclesFetchError();
                }
            });
        }
    }

    @Override
    public void openVehicleDetails(Vehicle vehicle) {
        vehicle = Preconditions.checkNotNull(vehicle, "Vehicle cannot be null");
        mView.showDetailVehicleUi(vehicle.getId());
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }
}
