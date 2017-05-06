package elsys.mycar.mycarpro.list.activities.services;

import com.google.common.base.Preconditions;

import java.util.List;

import elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepository;
import elsys.mycar.mycarpro.data.model.Service;
import elsys.mycar.mycarpro.data.model.Vehicle;

public class ListServicesPresenter implements ListServicesContract.Presenter {

    private String mVehicleId;
    private VehicleRepository mVehicleRepository;
    private ListServicesContract.View mView;
    private boolean mIsDataMissing;

    public ListServicesPresenter(String mVehicleId, VehicleRepository mVehicleRepository, ListServicesContract.View mView, boolean mIsDataMissing) {
        this.mVehicleId = mVehicleId;
        this.mVehicleRepository = Preconditions.checkNotNull(mVehicleRepository);
        this.mView = Preconditions.checkNotNull(mView);
        this.mIsDataMissing = mIsDataMissing;
    }

    @Override
    public void start() {
        if (mIsDataMissing && mView.isActive()) {
            loadItems();
        }
    }

    @Override
    public void loadItems() {
        if (mVehicleId == null) {
            mView.showNoSuchVehicle();
        }else {
            mView.showProgress();
            mVehicleRepository.getVehicleById(mVehicleId, new VehicleRepository.OnVehicleFetchedCallback() {
                @Override
                public void onSuccess(Vehicle vehicle) {
                    processServices(vehicle.getServices());
                }

                @Override
                public void onFailure() {
                    mView.showMessage("Something went wrong...");
                    mView.hideProgress();
                }
            });
        }
    }

    @Override
    public void onVehicleChanged(String vehicleId) {
        mVehicleId = vehicleId;
        mIsDataMissing = true;
    }

    @Override
    public void openServiceDetails(Service service) {
        mView.showDetailItemUi(Preconditions.checkNotNull(service).getId());
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }

    private void processServices(List<Service> services) {
        if (services == null || services.isEmpty()) {
            mView.showNoItemsFound();
        }else {
            mView.showServices(services);
            mIsDataMissing = false;
        }
        mView.hideProgress();
    }
}
