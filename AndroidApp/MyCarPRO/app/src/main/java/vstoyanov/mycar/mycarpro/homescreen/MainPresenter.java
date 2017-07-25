package vstoyanov.mycar.mycarpro.homescreen;

import com.google.common.base.Preconditions;

import java.util.List;

import vstoyanov.mycar.mycarpro.data.model.Vehicle;
import vstoyanov.mycar.mycarpro.data.repository.OnItemsFetchedCallback;
import vstoyanov.mycar.mycarpro.data.repository.vehicle.VehicleRepository;
import vstoyanov.mycar.mycarpro.util.DataUtils;

public class MainPresenter implements MainContract.Presenter {

    private String mUserId;
    private VehicleRepository mVehicleRepository;
    private MainContract.View mView;
    private boolean mIsDataMissing = true;

    public MainPresenter(String userId, VehicleRepository vehicleRepository, MainContract.View view) {
        this.mUserId = userId;
        this.mVehicleRepository = Preconditions.checkNotNull(vehicleRepository);
        this.mView = Preconditions.checkNotNull(view);
    }

    @Override
    public void start() {
        if (mIsDataMissing) {
            mVehicleRepository.fetchVehicles(mUserId, new OnItemsFetchedCallback<Vehicle>() {
                @Override
                public void onSuccess(List<Vehicle> items) {
                    mView.showVehicleItemsInDropdown(DataUtils.getVehicleIdsAndNames(items));
                    mIsDataMissing = false;
                }

                @Override
                public void onFailure() {
                    // TODO: 23.06.17 string literal should be removed!
                    mView.showMessage("Couldn't get your vehicles");
                }
            });
        }
    }

    @Override
    public void openAddEditVehicle() {
        mView.showAddEditVehicleUi();
    }

    @Override
    public void openAddEditService(String vehicleId) {
        mView.showAddEditServiceUi(vehicleId);
    }

    @Override
    public void openAddEditInsurance(String vehicleId) {
        mView.showAddEditInsuranceUi(vehicleId);
    }

    @Override
    public void openAddEditRefueling(String vehicleId) {
        mView.showAddEditRefuelingUi(vehicleId);
    }

    @Override
    public void openVehicles() {
        mView.showVehiclesUi();
    }

    @Override
    public void openActivities() {
        mView.showActivitiesUi();
    }

    @Override
    public void openStatistics() {
        mView.showStatisticsUi();
    }

    @Override
    public void openProfile() {
        mView.showProfileUi();
    }

    @Override
    public void setDataMissing() {
        mIsDataMissing = true;
    }
}
