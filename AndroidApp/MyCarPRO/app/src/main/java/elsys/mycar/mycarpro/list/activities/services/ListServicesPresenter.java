package elsys.mycar.mycarpro.list.activities.services;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

import elsys.mycar.mycarpro.data.VehicleRepository;
import elsys.mycar.mycarpro.model.Service;

public class ListServicesPresenter implements ListServiceContract.Presenter {

    private String mVehicleId;
    private ListServiceContract.View mView;
    private VehicleRepository mVehicleRepository;
    private boolean mIsDataMissing;

    public ListServicesPresenter(String mVehicleId, ListServiceContract.View mView, VehicleRepository mVehicleRepository, boolean mIsDataMissing) {
        this.mVehicleId = mVehicleId;
        this.mView = Preconditions.checkNotNull(mView);
        this.mVehicleRepository = Preconditions.checkNotNull(mVehicleRepository);
        this.mIsDataMissing = mIsDataMissing;
    }

    @Override
    public void start() {
        if (mVehicleId == null) {
            mView.showNoSuchVehicle();
        }else {
            if (mIsDataMissing) {
                List<Service> services = new ArrayList<>();
                services.add(new Service("asdasd", "Kurvi", "PETIO PEDAL", 12, 12, "note"));
                services.add(new Service("bghn", "PETIO PEDAL", "9 May 2012", 232, 323, "note"));
                services.add(new Service("fgbgrfhsdf", "PEDALI", "123 May 132213", 123, 65, "note"));
                mView.addServices(services/*mVehicleRepository.getServicesByVehicleId(mVehicleId)*/);
            }
        }
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }
}
