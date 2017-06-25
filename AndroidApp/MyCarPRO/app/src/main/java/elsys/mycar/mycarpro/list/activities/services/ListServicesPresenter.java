package elsys.mycar.mycarpro.list.activities.services;

import com.google.common.base.Preconditions;

import elsys.mycar.mycarpro.data.model.Service;
import elsys.mycar.mycarpro.data.repository.activities.service.ServiceRepository;
import elsys.mycar.mycarpro.list.base.BaseActivitiesContract;
import elsys.mycar.mycarpro.list.base.BaseActivitiesPresenter;

public class ListServicesPresenter extends BaseActivitiesPresenter<Service> {

    private ServiceRepository mServiceRepository;

    public ListServicesPresenter(String vehicleId, BaseActivitiesContract.View<Service> view, ServiceRepository serviceRepository, boolean isDataMissing) {
        super(vehicleId, view, isDataMissing);
        this.mServiceRepository = Preconditions.checkNotNull(serviceRepository, "ServiceRepository cannot be null");
    }

    @Override
    protected void fetchItems(String vehicleId) {
        mServiceRepository.fetchServices(vehicleId, this);
    }

    @Override
    public void openItemDetails(Service item) {
        item = Preconditions.checkNotNull(item, "Cannot open service details on a null object reference");
        view.showDetailsItemUi(item.getId());
    }
}
