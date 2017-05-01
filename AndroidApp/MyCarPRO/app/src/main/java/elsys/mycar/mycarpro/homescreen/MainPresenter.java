package elsys.mycar.mycarpro.homescreen;

import android.util.Log;

import com.google.common.collect.HashBiMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import elsys.mycar.mycarpro.data.VehicleRepository;

public class MainPresenter implements MainContract.Presenter {
    private VehicleRepository mVehicleRepository;
    private MainContract.View mView;
    private String mVehicleId;
    private boolean mIsDataMissing = true;

    public MainPresenter(VehicleRepository mVehicleRepository, MainContract.View mView) {
        this.mVehicleRepository = mVehicleRepository;
        this.mView = mView;
    }

    @Override
    public void start() {
        if (mIsDataMissing) {
            List<String> names = new ArrayList<>(mVehicleRepository.getVehicleIdToNameHash().values());
            mView.showVehicleNames(names);
            mIsDataMissing = false;
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
    public void onSelectedVehicleChanged(String vehicleName) {
        String vehicleId = mVehicleRepository.getVehicleIdByName(vehicleName);
        mView.setSelectedVehicleId(vehicleId);
        mVehicleId = vehicleId;

        //TODO: SEE THIS TODO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //TODO: adding and removing or optimizing those spinner item selected listeners making sure those vehicle ids are requested at the right time build view ui for detail view of given item

    }
}
