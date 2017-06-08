package elsys.mycar.mycarpro.loginscreen;

import elsys.mycar.mycarpro.base.BasePresenter;
import elsys.mycar.mycarpro.base.BaseView;

public interface LoginContract {


    interface View extends BaseView<Presenter> {

        void setUsername(String username);

        void showUsernameError(String error);

        void showPasswordError(String error);

        void showLoginFailed();

        void showProgress();

        void hideProgress();

        void showLoginSucceeded(String email);

        void continueToTheApp();

    }

    interface Presenter extends BasePresenter {

        void login(String username, String password);
    }
}
