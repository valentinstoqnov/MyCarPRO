package elsys.mycar.mycarpro.list.activities.services;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

import elsys.mycar.mycarpro.data.VehicleRepository;
import elsys.mycar.mycarpro.list.activities.ListActivitiesContract;
import elsys.mycar.mycarpro.model.Service;

public class ListServicesPresenter implements ListActivitiesContract.Presenter {

    private String mVehicleId;
    private VehicleRepository mVehicleRepository;
    private ListServiceContract.View mView;
    private boolean mIsDataMissing;

    public ListServicesPresenter(String mVehicleId, VehicleRepository mVehicleRepository, ListServiceContract.View mView, boolean mIsDataMissing) {
        this.mVehicleId = mVehicleId;
        this.mVehicleRepository = Preconditions.checkNotNull(mVehicleRepository);
        this.mView = Preconditions.checkNotNull(mView);
        this.mIsDataMissing = mIsDataMissing;
    }

    @Override
    public void start() {
        if (mVehicleId == null) {
            mView.showNoSuchVehicle();
            List<Service> services = new ArrayList<>();
            services.add(new Service("ads", "Windows", "09 May 2017", 2323, 124,"note"));
            services.add(new Service("adsasd", "Lights", "10 May 2017", 1030, 155,"note"));
            services.add(new Service("adxcvs", "Engine oil", "11 May 2017", 1231, 233,"note"));
            services.add(new Service("adsqwre", "Filters", "23 May 2017", 5434, 555,"note"));
            services.add(new Service("ads", "Windows", "09 May 2017", 2323, 124,"note"));
            services.add(new Service("adsasd", "Lights", "10 May 2017", 1030, 155,"note"));
            services.add(new Service("adxcvs", "Engine oil", "11 May 2017", 1231, 233,"note"));
            services.add(new Service("adsqwre", "Filters", "23 May 2017", 5434, 555,"note"));
            services.add(new Service("ads", "Windows", "09 May 2017", 2323, 124,"note"));
            services.add(new Service("adsasd", "Lights", "10 May 2017", 1030, 155,"note"));
            services.add(new Service("adxcvs", "Engine oil", "11 May 2017", 1231, 233,"note"));
            services.add(new Service("adsqwre", "Filters", "23 May 2017", 5434, 555,"note"));
            services.add(new Service("ads", "Windows", "09 May 2017", 2323, 124,"note"));
            services.add(new Service("adsasd", "Lights", "10 May 2017", 1030, 155,"note"));
            services.add(new Service("adxcvs", "Engine oil", "11 May 2017", 1231, 233,"note"));
            services.add(new Service("adsqwre", "Filters", "23 May 2017", 5434, 555,"note"));
            mView.addItems(services);
        }else if (mIsDataMissing){
            List<Service> services = mVehicleRepository.getServicesByVehicleId(mVehicleId);
            if (services == null || services.size() == 0) {
                mView.showNoItemsFound();
            }else {
                mView.addItems(services);
            }
        }
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }
}
