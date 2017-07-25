package vstoyanov.mycar.mycarpro.statistics.table;

import vstoyanov.mycar.mycarpro.base.BasePresenter;
import vstoyanov.mycar.mycarpro.base.BaseView;

public interface TableContract {

    interface View extends BaseView<Presenter> {

        void showMessage(String message);

        void setTotalVehicles(String count);

        void setTotalServices(String count);

        void setTotalInsurances(String count);

        void setTotalRefuelings(String count);

        void setServiceExpenses(String expenses);

        void setInsurnaceExpenses(String expenses);

        void setRefuelingExpenses(String expenses);
    }

    interface Presenter extends BasePresenter {

    }
}
