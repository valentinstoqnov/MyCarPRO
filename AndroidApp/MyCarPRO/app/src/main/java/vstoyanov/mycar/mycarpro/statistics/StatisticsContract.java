package vstoyanov.mycar.mycarpro.statistics;

import vstoyanov.mycar.mycarpro.base.BasePresenter;
import vstoyanov.mycar.mycarpro.base.BaseView;

public interface StatisticsContract {

    interface View extends BaseView<Presenter> {

        void showMessage(String message);
    }

    interface Presenter extends BasePresenter {

        void onVehicleChanged(String vehicleId);

    }
}
