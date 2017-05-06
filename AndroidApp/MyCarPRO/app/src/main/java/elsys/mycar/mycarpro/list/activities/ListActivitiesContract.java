package elsys.mycar.mycarpro.list.activities;

import elsys.mycar.mycarpro.base.BasePresenter;
import elsys.mycar.mycarpro.base.BaseView;

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
