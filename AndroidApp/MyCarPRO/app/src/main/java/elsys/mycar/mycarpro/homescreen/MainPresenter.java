package elsys.mycar.mycarpro.homescreen;

import android.util.Log;

import com.google.common.collect.HashBiMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import elsys.mycar.mycarpro.data.VehicleRepository;

public class MainPresenter implements MainContract.Presenter {

    private HashBiMap<String, String> mVehicleIdToNameHash;
    private VehicleRepository mVehicleRepository;
    private MainContract.View mView;
    private String mVehicleId;

    public MainPresenter(VehicleRepository mVehicleRepository, MainContract.View mView) {
        this.mVehicleRepository = mVehicleRepository;
        this.mView = mView;
    }
/*
    @Override
    public void start() {
        mVehicleIdToNameHash = HashBiMap.create(mVehicleRepository.getVehicleIdToNameHash());
        mView.setVehicleNames(new ArrayList<>(mVehicleIdToNameHash.values()));
        //mView.setSelectedVehicleId(mVehicleRepository.getVehicleIdByName(mVehicleIdToNameHash.keySet().iterator().next()));
    }*/

    @Override
    public void start() {

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
    public void onSelectedVehicleChanged(String vehicleName) {
        String vehicleId = mVehicleIdToNameHash.inverse().get(vehicleName);
        mView.setSelectedVehicleId(vehicleId);
        mVehicleId = vehicleId;
    }

/*    @Override
    public void start() {
        mVehicleIdToNameHash = HashBiMap.create(mVehicleRepository.getVehicleIdToNameHash());
        mView.setVehicleNames(new ArrayList<>(mVehicleIdToNameHash.values()));
        mView.setSelectedVehicleId(mVehicleRepository.getVehicleIdByName(mVehicleIdToNameHash.keySet().iterator().next()));
    }

    @Override
    public void onSelectedVehicleChanged(String vehicleName) {
        String vehicleId = mVehicleIdToNameHash.inverse().get(vehicleName);
        mView.setSelectedVehicleId(vehicleId);
    }*/
}
