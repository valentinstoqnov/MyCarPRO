package elsys.mycar.mycarpro.list.activities;

import java.util.List;

import elsys.mycar.mycarpro.base.BasePresenter;
import elsys.mycar.mycarpro.base.BaseView;
import elsys.mycar.mycarpro.model.Insurance;
import elsys.mycar.mycarpro.model.Refueling;
import elsys.mycar.mycarpro.model.Service;

public interface ListActivitiesContract {

    interface View<T> extends BaseView<Presenter> {

        void addItems(List<T> items);

        void showNoItemsFound();

        void showNoSuchVehicle();
    }

    interface Presenter extends BasePresenter{

        boolean isDataMissing();
    }
}
