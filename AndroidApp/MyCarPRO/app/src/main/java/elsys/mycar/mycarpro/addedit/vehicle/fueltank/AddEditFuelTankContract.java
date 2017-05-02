package elsys.mycar.mycarpro.addedit.vehicle.fueltank;

import java.util.List;

import elsys.mycar.mycarpro.base.BasePresenter;
import elsys.mycar.mycarpro.base.BaseView;

public interface AddEditFuelTankContract {

    interface View extends BaseView<Presenter> {

        void showFuelTypes(List<String> items);

        void showCapacityError(String message);

        void showConsumptionError(String message);

        void onInputValidated(String fuelType, int capacity, double consumption);

    }

    interface Presenter extends BasePresenter {

        void addFuelTank(String fuelType, String capacity, String consumption);
    }
}
