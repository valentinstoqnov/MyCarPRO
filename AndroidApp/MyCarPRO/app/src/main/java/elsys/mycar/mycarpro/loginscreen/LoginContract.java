package elsys.mycar.mycarpro.loginscreen;

/**
 * Created by valio_stoyanov on 02.05.17.
 */

public interface LoginContract {


    void setEmail(String email);

    void showEmailError(String error);

    void showPasswordError(String error);

    void showNoSuchUser();

    void showProgress();

    void hideProgress();

    void loggedIn();
}

interface Presenter extends BasePresenter {

    void login(String email, String password);
}
}
