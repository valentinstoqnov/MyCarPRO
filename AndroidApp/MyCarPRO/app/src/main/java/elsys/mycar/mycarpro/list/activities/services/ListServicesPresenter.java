package elsys.mycar.mycarpro.list.activities.services;

import com.google.common.base.Preconditions;
import com.google.gson.internal.bind.util.ISO8601Utils;

import java.util.List;

import elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepository;
import elsys.mycar.mycarpro.data.model.Service;
import elsys.mycar.mycarpro.data.model.Vehicle;

public class ListServicesPresenter implements ListServicesContract.Presenter {

    private ListServicesContract.View mView;
    private boolean mIsDataMissing;

    @Override
    public void start() {

    }

    @Override
    public void openItemDetails(Service item) {

    }

    @Override
    public void swapDataSet(List<Service> items) {

    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }
}
