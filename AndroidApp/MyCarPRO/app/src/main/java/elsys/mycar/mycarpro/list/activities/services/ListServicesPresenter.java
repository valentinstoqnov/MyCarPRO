package elsys.mycar.mycarpro.list.activities.services;

import android.util.Log;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

import elsys.mycar.mycarpro.data.VehicleRepository;
import elsys.mycar.mycarpro.list.activities.ListActivitiesContract;
import elsys.mycar.mycarpro.model.Service;

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
            List<Service> s = new ArrayList<Service>();
            s.add(new Service("12edsxa", "IDK", "2 May 2020", 2020, 200, "note"));
            s.add(new Service("12edsxaasd", "Sad story", "2 May 2012", 2020, 200, "note"));
            s.add(new Service("12edsxah", "IDK - asd", "20 Apr 2020", 2020, 200, "note"));
            s.add(new Service("12edsxawer3", "adal;d.asd;", "01 Jan 2012", 2020, 200, "note"));
            processServices(s);
            //mView.showNoSuchVehicle();
        }else {
            List<Service> services = mVehicleRepository.getServicesByVehicleId(mVehicleId);
            processServices(services);
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
    }
}
