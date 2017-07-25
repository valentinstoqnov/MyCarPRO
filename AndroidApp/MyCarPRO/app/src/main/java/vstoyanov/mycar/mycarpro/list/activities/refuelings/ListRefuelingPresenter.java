package vstoyanov.mycar.mycarpro.list.activities.refuelings;

import com.google.common.base.Preconditions;

import vstoyanov.mycar.mycarpro.data.model.Refueling;
import vstoyanov.mycar.mycarpro.data.repository.activities.refueling.RefuelingRepository;
import vstoyanov.mycar.mycarpro.list.base.BaseActivitiesContract;
import vstoyanov.mycar.mycarpro.list.base.BaseActivitiesPresenter;

public class ListRefuelingPresenter extends BaseActivitiesPresenter<Refueling> {

    private RefuelingRepository mRefuelingRepository;

    public ListRefuelingPresenter(String vehicleId, BaseActivitiesContract.View<Refueling> view, RefuelingRepository refuelingRepository, boolean isDataMissing) {
        super(vehicleId, view, isDataMissing);
        this.mRefuelingRepository = refuelingRepository;
    }

    @Override
    protected void fetchItems(String vehicleId) {
        mRefuelingRepository.fetchRefuelings(vehicleId, this);
    }

    @Override
    public void openItemDetails(Refueling item) {
        item = Preconditions.checkNotNull(item, "Cannot open refueling details on a null object reference");
        view.showDetailsItemUi(item.getId());
    }
}
