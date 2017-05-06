package elsys.mycar.mycarpro.homescreen;

import com.google.common.base.Preconditions;

import java.util.List;

import elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepository;
import elsys.mycar.mycarpro.model.Vehicle;
import elsys.mycar.mycarpro.util.DataUtils;

public class HomePresenter implements MainContract.HomePresenter {

    private List<String> mVehicleIds;
    private VehicleRepository mVehicleRepository;
    private MainContract.HomewView mView;
    private boolean mIsDataMissing = true;

    public HomePresenter(VehicleRepository mVehicleRepository, MainContract.HomewView mView) {
        this.mVehicleRepository = Preconditions.checkNotNull(mVehicleRepository);
        this.mView = Preconditions.checkNotNull(mView);
    }

    @Override
    public void start() {
        if (mIsDataMissing) {
            mVehicleRepository.getVehicles(new VehicleRepository.OnVehiclesFetchedCallback() {
                @Override
                public void onSuccess(List<Vehicle> vehicles) {
                    mVehicleIds = DataUtils.getVehicleIds(vehicles);
                    mView.showVehicleNames(DataUtils.getVehicleNames(vehicles));
                    mIsDataMissing = false;
                }

                @Override
                public void onFailure() {
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
    public void openAddEditService(int position) {
        mView.showAddEditServiceUi(getVehicleIdByPosition(position));
    }

    @Override
    public void openAddEditInsurance(int position) {
        mView.showAddEditInsuranceUi(getVehicleIdByPosition(position));
    }

    @Override
    public void openAddEditRefueling(int position) {
        mView.showAddEditRefuelingUi(getVehicleIdByPosition(position));
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

    private String getVehicleIdByPosition(int position) {
        return mVehicleIds.get(position);
    }
}
