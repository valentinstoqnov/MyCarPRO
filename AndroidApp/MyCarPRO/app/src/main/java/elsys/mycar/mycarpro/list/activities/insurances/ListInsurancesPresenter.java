package elsys.mycar.mycarpro.list.activities.insurances;

import com.google.common.base.Preconditions;

import java.util.List;

import elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepository;
import elsys.mycar.mycarpro.data.model.Insurance;
import elsys.mycar.mycarpro.data.model.Vehicle;

public class ListInsurancesPresenter implements ListInsurancesContract.Presenter{

    private String mVehicleId;
    private VehicleRepository mVehicleRepository;
    private ListInsurancesContract.View mView;
    private boolean mIsDataMissing;

    public ListInsurancesPresenter(String mVehicleId, VehicleRepository mVehicleRepository, ListInsurancesContract.View mView, boolean mIsDataMissing) {
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
    public void onVehicleChanged(String vehicleId) {
        mVehicleId = vehicleId;
        mIsDataMissing = true;
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
                    processInsurances(vehicle.getInsurances());
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
    public void openInsuranceDetails(Insurance insurance) {
        mView.showDetailItemUi(Preconditions.checkNotNull(insurance).getId());
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }

    private void processInsurances(List<Insurance> insurances) {
        if (insurances == null || insurances.isEmpty()) {
            mView.showNoItemsFound();
        }else {
            mView.showInsurances(insurances);
            mIsDataMissing = false;
        }
        mView.hideProgress();
    }

}