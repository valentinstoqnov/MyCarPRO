package elsys.mycar.mycarpro.homescreen;

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

    }

    @Override
    public String getVehicleIdByName(String name) {
        return mVehicleRepository.getVehicleIdByName(name);
    }

    @Override
    public void requestVehicleNames() {
        List<String> names = mVehicleRepository.getAllVehicleNames();
        mView.setVehicleNames(names);
    }
}
