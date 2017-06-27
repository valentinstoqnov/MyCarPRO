package elsys.mycar.mycarpro.list.base;

import com.google.common.base.Preconditions;

import java.util.List;

import elsys.mycar.mycarpro.data.repository.OnItemsFetchedCallback;

public abstract class BaseActivitiesPresenter<T> implements BaseActivitiesContract.Presenter<T>, OnItemsFetchedCallback<T> {

    private String mVehicleId;
    private boolean mIsDataMissing;
    protected BaseActivitiesContract.View<T> view;
    protected abstract void fetchItems(String vehicleId);

    public BaseActivitiesPresenter(String vehicleId, BaseActivitiesContract.View<T> view, boolean isDataMissing) {
        this.mVehicleId = Preconditions.checkNotNull(vehicleId, "Vehicle id cannot be null");
        this.view = Preconditions.checkNotNull(view, "BaseActivitiesContract.View cannot be null");
        this.mIsDataMissing = isDataMissing;
    }

    @Override
    public void start() {
        if (mIsDataMissing) {
            view.showProgress();
            fetchItems(mVehicleId);
        }
    }

    @Override
    public void onVehicleChange(String vehicleId) {
        mVehicleId = Preconditions.checkNotNull(vehicleId, "Vehicle id cannot be null");
        mIsDataMissing = true;
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }

    @Override
    public void onSuccess(List<T> items) {
        view.showItems(items);
        if (items.isEmpty()) {
            view.showNoItemsFound();
        }
        mIsDataMissing = false;
        view.hideProgress();
    }

    @Override
    public void onFailure() {
        view.hideProgress();
        view.showItemsFetchError();
        mIsDataMissing = true;
    }
}
