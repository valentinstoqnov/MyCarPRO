package elsys.mycar.mycarpro.registerscreen;

import elsys.mycar.mycarpro.base.BasePresenter;
import elsys.mycar.mycarpro.base.BaseView;

/**
 * Created by valio_stoyanov on 02.05.17.
 */

public interface RegisterContract {


    interface View extends BaseView<Presenter> {

        void showFirstNameError(String error);

        void showLastNameError(String error);

        void showEmailError(String error);

        void showPasswordError(String error);

        void showAccountExistsError();

        void showAuthenticating();

        void hideAuthenticating();

        void registered(String email);
    }

    interface Presenter extends BasePresenter {

        void register(String firstName, String lastName, String email, String password);
    }
}
