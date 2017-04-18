package elsys.mycar.mycarpro.list.activities.services;

import java.util.List;

import elsys.mycar.mycarpro.base.BasePresenter;
import elsys.mycar.mycarpro.base.BaseView;
import elsys.mycar.mycarpro.model.Service;

public interface ListServiceContract {

    interface View extends BaseView<Presenter> {

        void addServices(List<Service> items);

        void showNoSuchVehicle();
    }

    interface Presenter extends BasePresenter {


        boolean isDataMissing();
    }
}
