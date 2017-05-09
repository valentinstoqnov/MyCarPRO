package elsys.mycar.mycarpro.list.activities.services;

import java.util.List;

import elsys.mycar.mycarpro.data.model.Service;

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
