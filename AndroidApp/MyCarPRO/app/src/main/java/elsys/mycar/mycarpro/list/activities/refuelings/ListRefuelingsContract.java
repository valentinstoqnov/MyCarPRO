package elsys.mycar.mycarpro.list.activities.refuelings;

import java.util.List;

import elsys.mycar.mycarpro.data.model.Refueling;
import elsys.mycar.mycarpro.list.activities.ListActivitiesContract;

public interface ListRefuelingsContract {

    interface View extends ListActivitiesContract.View<Presenter, Presenter> {

        void showRefuelings(List<Refueling> items);
    }

    interface Presenter extends ListActivitiesContract.Presenter {

        void openRefuelingDetails(Refueling refueling);
    }
}
