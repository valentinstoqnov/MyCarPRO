package elsys.mycar.mycarpro.list.activities.services;

import elsys.mycar.mycarpro.data.model.Service;
import elsys.mycar.mycarpro.list.activities.ListActivitiesContract;

public interface ListServicesContract {

    interface View extends ListActivitiesContract.View<Presenter, Presenter>{

     /*   void showServices(List<Service> items);

        void showMessage(String message);*/
    }

    interface Presenter extends ListActivitiesContract.Presenter<Service> {

        /*void start(List<Service> asd);

        void openServiceDetails(Service service);*/
    }
}
