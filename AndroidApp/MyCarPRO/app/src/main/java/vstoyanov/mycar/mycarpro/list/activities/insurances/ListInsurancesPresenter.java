package vstoyanov.mycar.mycarpro.list.activities.insurances;

import com.google.common.base.Preconditions;

import vstoyanov.mycar.mycarpro.data.model.Insurance;
import vstoyanov.mycar.mycarpro.data.repository.activities.insurance.InsuranceRepository;
import vstoyanov.mycar.mycarpro.list.base.BaseActivitiesContract;
import vstoyanov.mycar.mycarpro.list.base.BaseActivitiesPresenter;

public class ListInsurancesPresenter extends BaseActivitiesPresenter<Insurance> {

    private InsuranceRepository mInsuranceRepository;

    public ListInsurancesPresenter(String vehicleId, BaseActivitiesContract.View<Insurance> view, InsuranceRepository insuranceRepository, boolean isDataMissing) {
        super(vehicleId, view, isDataMissing);
        this.mInsuranceRepository = Preconditions.checkNotNull(insuranceRepository, "InsuranceRepository cannot be null");
    }

    @Override
    protected void fetchItems(String vehicleId) {
        mInsuranceRepository.fetchInsurances(vehicleId, this);
    }

    @Override
    public void openItemDetails(Insurance item) {
        item = Preconditions.checkNotNull(item, "Cannot open insurance details on a null object reference");
        view.showDetailsItemUi(item.getId());
    }
}