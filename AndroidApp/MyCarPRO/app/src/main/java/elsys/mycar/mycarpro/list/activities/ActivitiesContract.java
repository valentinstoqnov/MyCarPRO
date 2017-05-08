package elsys.mycar.mycarpro.list.activities;

import java.util.List;

import elsys.mycar.mycarpro.base.BasePresenter;
import elsys.mycar.mycarpro.base.BaseView;
import elsys.mycar.mycarpro.data.model.Insurance;
import elsys.mycar.mycarpro.data.model.Refueling;
import elsys.mycar.mycarpro.data.model.Service;

public interface ActivitiesContract {

    interface View extends BaseView<Presenter> {

        void showVehicleContent(String vehicleId);
    }

    interface Presenter extends BasePresenter {

        void onVehicleChange(String vehicleName);
    }

    interface View1 extends BaseView<Presenter1> {

        void showMessage(String message);

        void showServices(List<Service> services);

        void showInsurances(List<Insurance> insurances);

        void showRefueling(List<Refueling> refuelings);

        //void showVehicleActivities(List<Service> services, List<Insurance> insurances, List<Refueling> refuelings);
    }

    interface Presenter1 extends BasePresenter {

        void onVehicleChanged(String vehicleId);

        void provideServices();

        void provideInsurances();

        void provideRefuelings();
    }
}
