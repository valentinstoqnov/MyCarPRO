package elsys.mycar.mycarpro.loginscreen;

import elsys.mycar.mycarpro.data.repository.user.UserRepository;
import elsys.mycar.mycarpro.model.User;

import static com.google.common.base.Preconditions.checkNotNull;

public class LoginPresenter {

    private LoginContract.View mView;
    private UserRepository mUserRepository;

    public LoginPresenter(LoginContract.View mView, UserRepository mUserRepository) {
        this.mView = checkNotNull(mView);
        this.mUserRepository = checkNotNull(mUserRepository);
    }

    @Override
    public void start() {

    }

    @Override
    public void login(String email, String password) {
        mView.showProgress();

        User user = new User(email, password);

        if (validate(user)) {
            if (mUserRepository.exist(user)) {
                mView.loggedIn();
            } else {
                mView.showNoSuchUser();
            }
        }

        mView.hideProgress();
    }

    private boolean validate(User user) {
        boolean valid = true;

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            valid = false;
            mView.showEmailError("Invalid email");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            valid = false;
            mView.showPasswordError("Invalid password");
        }

        return valid;
    }
}
