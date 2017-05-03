package elsys.mycar.mycarpro.list.activities.services;

import java.util.List;

import elsys.mycar.mycarpro.list.activities.ListActivitiesContract;
import elsys.mycar.mycarpro.model.Service;

public interface ListServicesContract {

    interface View extends ListActivitiesContract.View<Presenter>{

        void showServices(List<Service> items);

        void showMessage(String message);
    }

    interface Presenter extends ListActivitiesContract.Presenter {

        void openServiceDetails(Service service);
    }
}
