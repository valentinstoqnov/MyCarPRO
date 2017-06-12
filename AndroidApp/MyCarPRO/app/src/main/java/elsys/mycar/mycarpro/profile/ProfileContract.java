package elsys.mycar.mycarpro.profile;

import elsys.mycar.mycarpro.base.BasePresenter;
import elsys.mycar.mycarpro.base.BaseView;

public interface ProfileContract {

    interface View extends BaseView<Presenter> {

        void showProgress();

        void hideProgress();

        void showFailedToFindSuchUser();

        void setEmail(String email);

        void setFirstName(String firstName);

        void setLastName(String lastName);
    }

    interface Presenter extends BasePresenter {

        void signOutCurrentUser();

        void editAccount(String userId);
    }
}
