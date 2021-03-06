package vstoyanov.mycar.mycarpro.profile;

import vstoyanov.mycar.mycarpro.base.BasePresenter;
import vstoyanov.mycar.mycarpro.base.BaseView;

public interface ProfileContract {

    interface View extends BaseView<Presenter> {

        void showProgress();

        void hideProgress();

        void showFailedToFindSuchUser();

        void setEmail(String email);

        void setFirstName(String firstName);

        void setLastName(String lastName);

        void showSettingsUi();

        void showEditProfileUi(String id);
    }

    interface Presenter extends BasePresenter {

        void openSettings();

        void openEditProfile();

        void signOutCurrentUser();

        boolean isDataMissing();
    }
}
