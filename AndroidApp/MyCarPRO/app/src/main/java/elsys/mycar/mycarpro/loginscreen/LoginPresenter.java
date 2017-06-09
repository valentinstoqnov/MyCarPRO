package elsys.mycar.mycarpro.loginscreen;

import elsys.mycar.mycarpro.data.repository.user.UserRepository;
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
    public void start() {}

    @Override
    public void login(String email, String password) {
        mView.showProgress();
        if (StringUtils.checkNotNullOrEmpty(email)) {
            if (StringUtils.checkNotNullOrEmpty(password)) {
                mUserRepository.loginUser(email, password, new UserRepository.OnUserSignCallback() {
                    @Override
                    public void onSuccess(String email) {
                        mView.hideProgress();
                        mView.showLoginSucceeded(email);
                        mView.continueToTheApp();
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
            mView.showEmailError("Username not filled in");
            mView.hideProgress();
        }
    }
}
