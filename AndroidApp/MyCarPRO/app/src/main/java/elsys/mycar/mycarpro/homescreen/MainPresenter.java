package elsys.mycar.mycarpro.homescreen;

import com.google.common.base.Preconditions;

import java.util.List;

import elsys.mycar.mycarpro.data.model.Vehicle;
import elsys.mycar.mycarpro.data.repository.OnItemsFetchedCallback;
import elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepository;
import elsys.mycar.mycarpro.util.DataUtils;

public class MainPresenter implements MainContract.Presenter {

    //private List<String> mVehicleIds;
    private VehicleRepository mVehicleRepository;
    private MainContract.View mView;
    private boolean mIsDataMissing = true;

    public MainPresenter(VehicleRepository mVehicleRepository, MainContract.View mView) {
        this.mVehicleRepository = Preconditions.checkNotNull(mVehicleRepository);
        this.mView = Preconditions.checkNotNull(mView);
    }

    @Override
    public void start() {
        //if (mIsDataMissing) {
           /* mVehicleRepository.fetchVehicles(mView.getCurrentUserId(), new OnItemsFetchedCallback<Vehicle>() {
                @Override
                public void onSuccess(List<Vehicle> items) {
                    mView.showVehicleItemsInDropdown(DataUtils.getVehicleIdsAndNames(items));
                    mIsDataMissing = false;
                }

                @Override
                public void onFailure() {
                    mView.showMessage("Couldn't get your vehicles");
                }
            });*/
       // }
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

   /* private String getVehicleIdByPosition(int position) {
        return mVehicleIds.get(position);
    }*/
}
