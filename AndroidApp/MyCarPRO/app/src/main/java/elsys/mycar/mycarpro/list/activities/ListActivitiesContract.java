package elsys.mycar.mycarpro.list.activities;

import java.util.List;
import java.util.Objects;

import elsys.mycar.mycarpro.base.BasePresenter;
import elsys.mycar.mycarpro.base.BaseView;
import elsys.mycar.mycarpro.model.Insurance;
import elsys.mycar.mycarpro.model.Refueling;
import elsys.mycar.mycarpro.model.Service;

public interface ListActivitiesContract {

    interface View<T> extends BaseView<T> {

        void showDetailItemUi(String itemId);

        void showNoItemsFound();

        void showNoSuchVehicle();

        void showProgress();

        void hideProgress();

        void showMessage(String message);

        boolean isActive();
    }

    interface Presenter extends BasePresenter{

        void loadItems();

        void onVehicleChanged(String vehicleId);

        boolean isDataMissing();
    }
}
