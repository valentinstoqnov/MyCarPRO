package vstoyanov.mycar.mycarpro.registerscreen;

import vstoyanov.mycar.mycarpro.base.BasePresenter;
import vstoyanov.mycar.mycarpro.base.BaseView;

public interface RegisterContract {


    interface View extends BaseView<Presenter> {

        void showFirstNameError(String error);

        void showLastNameError(String error);

        void showEmailError(String error);

        void showPasswordError(String error);

        void showAuthenticating();

        void hideAuthenticating();

        void showRegisterFailed();

        void showRegisterSucceeded(String email);

        void continueToTheApp();
    }

    interface Presenter extends BasePresenter {

        void register(String firstName, String lastName, String email, String password);
    }
}
