package elsys.mycar.mycarpro.list.activities.insurances;

import java.util.List;

import elsys.mycar.mycarpro.data.model.Insurance;
import elsys.mycar.mycarpro.list.activities.ListActivitiesContract;

public interface ListInsurancesContract {

    interface View extends ListActivitiesContract.View<Presenter, Presenter> {

        void showInsurances(List<Insurance> items);
    }

    interface Presenter extends ListActivitiesContract.Presenter {

        void openInsuranceDetails(Insurance insurance);
    }
}
