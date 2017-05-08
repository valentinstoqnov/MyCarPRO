package elsys.mycar.mycarpro.list.idk;

import com.google.common.base.Preconditions;

import java.util.List;

import elsys.mycar.mycarpro.data.model.Insurance;
import elsys.mycar.mycarpro.data.model.Refueling;
import elsys.mycar.mycarpro.data.model.Service;
import elsys.mycar.mycarpro.list.activities.ListActivitiesContract;

public class IDKPresenter<T> implements ListActivitiesContract.Presenter<T> {

    private ListActivitiesContract.View<ListActivitiesContract.Presenter<T>, T> mView;
    private boolean mIsDataMissing;

    public IDKPresenter(ListActivitiesContract.View<ListActivitiesContract.Presenter<T>, T> mView, boolean mIsDataMissing) {
        this.mView = mView;
        this.mIsDataMissing = mIsDataMissing;
    }

    @Override
    public void start() {

    }

    @Override
    public void openItemDetails(T item) {
        item = Preconditions.checkNotNull(item, "cannot open details for null item");

        if (item instanceof Service) {
            mView.showDetailItemUi(((Service) item).getId());
        }else if (item instanceof Insurance) {
            mView.showDetailItemUi(((Insurance) item).getId());
        }else if (item instanceof Refueling) {
            mView.showDetailItemUi(((Refueling) item).getId());
        }else {
            throw new RuntimeException("cannot open item details for item that differs from Service, Insurance, Refueling");
        }
    }

    @Override
    public void swapDataSet(List<T> items) {
        if (mView.isActive()) {
            processDataSet(items);
        }else {
            mIsDataMissing = true;
        }
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }

    private void processDataSet(List<T> items) {
        if (items == null || items.isEmpty()) {
            mView.showNoItemsFound();
            mIsDataMissing = true;
        }else {
            mView.showItems(items);
            mIsDataMissing = false;
        }
    }
}
