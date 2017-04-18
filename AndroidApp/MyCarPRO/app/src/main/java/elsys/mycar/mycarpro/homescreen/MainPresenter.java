package elsys.mycar.mycarpro.homescreen;

import android.util.Log;

import java.util.List;

import elsys.mycar.mycarpro.data.VehicleRepository;

public class MainPresenter implements MainContract.Presenter {

    private VehicleRepository mVehicleRepository;
    private MainContract.View mView;

    public MainPresenter(VehicleRepository mVehicleRepository, MainContract.View mView) {
        this.mVehicleRepository = mVehicleRepository;
        this.mView = mView;
    }

    @Override
    public void start() {
        List<String> names = mVehicleRepository.getAllVehicleNames();
        mView.setVehicleNames(names);
        mView.setSelectedVehicleId(mVehicleRepository.getVehicleIdByName(names.get(0)));
    }

    @Override
    public void onSelectedVehicleChanged(String vehicleName) {
        String vehicleId = mVehicleRepository.getVehicleIdByName(vehicleName);
        Log.d("presenter vehicle id", "id = " + vehicleId);
        mView.setSelectedVehicleId(vehicleId);
    }
}
