package elsys.mycar.mycarpro.loginscreen;

import elsys.mycar.mycarpro.data.repository.user.UserRepository;
import elsys.mycar.mycarpro.data.model.User;
import elsys.mycar.mycarpro.util.StringUtils;

import static com.google.common.base.Preconditions.checkNotNull;

public class LoginPresenter implements LoginContract.Presenter{

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
    public void login(String username, String password) {
        mView.showProgress();

        if (StringUtils.checkNotNullOrEmpty(username)) {
            if (StringUtils.checkNotNullOrEmpty(password)) {
                mUserRepository.loginUser(username, password, new UserRepository.OnUserLoggedInCallback() {
                    @Override
                    public void onSuccess(String token, User user) {
                        mView.hideProgress();
                        mView.loggedIn(token, user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail());
                    }

                    @Override
                    public void onFailure() {
                        mView.hideProgress();
                        mView.showLoginFailed();
                    }
                });
            }else {
                mView.showPasswordError("Password not filled in");
                mView.hideProgress();
            }
        }else {
            mView.showUsernameError("Username not filled in");
            mView.hideProgress();
        }
    }
}
