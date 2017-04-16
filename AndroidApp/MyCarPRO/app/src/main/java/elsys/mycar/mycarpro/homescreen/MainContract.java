package elsys.mycar.mycarpro.homescreen;

import java.util.List;

import elsys.mycar.mycarpro.base.BasePresenter;
import elsys.mycar.mycarpro.base.BaseView;

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void setVehicleNames(List<String> items);
    }

    interface Presenter extends BasePresenter {

        void requestVehicleNames();

        String getVehicleIdByName(String name);
    }
}
