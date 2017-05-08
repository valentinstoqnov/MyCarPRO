package elsys.mycar.mycarpro.homescreen;

import com.google.common.base.Preconditions;

import java.util.List;

import elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepository;
import elsys.mycar.mycarpro.data.model.Vehicle;
import elsys.mycar.mycarpro.util.DataUtils;

public class MainPresenter implements MainContract.Presenter {

   // private HashBiMap<String, String> mVehicleIdsToNames;
    private List<String> mVehicleIds;
    private VehicleRepository mVehicleRepository;
    private MainContract.View mView;
    private String mVehicleId;
    private boolean mIsDataMissing = true;

    public MainPresenter(VehicleRepository vehicleRepository, MainContract.View view) {
        mVehicleRepository = Preconditions.checkNotNull(vehicleRepository);
        mView = Preconditions.checkNotNull(view);
    }

    @Override
    public void start() {
        if (mIsDataMissing) {
            mVehicleRepository.getVehicles(new VehicleRepository.OnVehiclesFetchedCallback() {
                @Override
                public void onSuccess(List<Vehicle> vehicles) {
                  //  mVehicleIdsToNames = DataUtils.getVehicleIdsAndNamesToHash(vehicles);
                    mVehicleIds = DataUtils.getVehicleIds(vehicles);
                    mView.showVehicleNames(DataUtils.getVehicleIdsAndNames(vehicles));
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
    public void openAddEditService() {
        mView.showAddEditServiceUi(mVehicleId);
    }

    @Override
    public void openAddEditInsurance() {
        mView.showAddEditInsuranceUi(mVehicleId);
    }

    @Override
    public void openAddEditRefueling() {
        mView.showAddEditRefuelingUi(mVehicleId);
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
    public void onSelectedVehicleChanged(int position) {
       // String vehicleId = mVehicleRepository.getVehicleIdByName(vehicleName);
       // mView.setSelectedVehicleId(vehicleId);
        //mVehicleId = vehicleId;

        mVehicleId = mVehicleIds.get(position);
        mView.setSelectedVehicleId(mVehicleId);


        //TODO: SEE THIS TODO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //TODO: adding and removing or optimizing those spinner item selected listeners making sure those vehicle ids are requested at the right time build view ui for detail view of given item

    }
}
