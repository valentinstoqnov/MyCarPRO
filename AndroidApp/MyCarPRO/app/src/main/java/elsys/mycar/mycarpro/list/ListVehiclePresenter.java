package elsys.mycar.mycarpro.list;

import com.google.common.base.Preconditions;

import elsys.mycar.mycarpro.data.VehicleRepository;

public class ListVehiclePresenter implements ListVehicleContract.Presenter{

    private VehicleRepository mVehicleRepository;
    private ListVehicleContract.View mView;

    public ListVehiclePresenter(VehicleRepository mVehicleRepository, ListVehicleContract.View mView) {
        this.mVehicleRepository = Preconditions.checkNotNull(mVehicleRepository);
        this.mView = Preconditions.checkNotNull(mView);
    }

    @Override
    public void start() {
        mView.addVehicles(mVehicleRepository.getAll());
    }

    @Override
    public void deleteVehicle(String vehicleId) {
        mVehicleRepository.delete(vehicleId);
    }
}
