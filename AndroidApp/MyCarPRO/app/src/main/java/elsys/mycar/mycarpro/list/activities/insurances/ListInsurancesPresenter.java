package elsys.mycar.mycarpro.list.activities.insurances;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

import elsys.mycar.mycarpro.data.VehicleRepository;
import elsys.mycar.mycarpro.list.activities.ListActivitiesContract;
import elsys.mycar.mycarpro.model.Insurance;

public class ListInsurancesPresenter implements ListActivitiesContract.Presenter {

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
        if (mVehicleId == null) {
            mView.showNoSuchVehicle();
            List<Insurance> insurances = new ArrayList<>();
            insurances.add(new Insurance("qwe","Armeec", 1233, 1232, "23 Apr 2016", "25 Apr 2017", "note"));
            insurances.add(new Insurance("qwe","Allianz", 32456, 1232, "15 Apr 2016", "25 Apr 2017", "note"));
            insurances.add(new Insurance("qwe","DZI", 2334, 1232, "20 Jan 2016", "25 Apr 2017", "note"));
            insurances.add(new Insurance("qwe","Armeec", 234, 1232, "01 Apr 2016", "25 Apr 2017", "note"));
            mView.addItems(insurances);
        }else if (mIsDataMissing){
            List<Insurance> insurances = mVehicleRepository.getInsurancesByVehicleId(mVehicleId);
            if (insurances == null || insurances.size() == 0) {
                mView.showNoItemsFound();
            }else {
                mView.addItems(insurances);
            }
        }
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }
}
