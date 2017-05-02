package elsys.mycar.mycarpro.registerscreen;

import elsys.mycar.mycarpro.base.BasePresenter;
import elsys.mycar.mycarpro.base.BaseView;

public interface RegisterContract {


    interface View extends BaseView<Presenter> {

        void showUserNameError(String error);

        void showFirstNameError(String error);

        void showLastNameError(String error);

        void showEmailError(String error);

        void showPasswordError(String error);

        void showAuthenticating();

        void hideAuthenticating();

        void showRegisterFailed();

        void registered(String username);
    }

    interface Presenter extends BasePresenter {

        void register(String username, String firstName, String lastName, String email, String password);
    }
}
