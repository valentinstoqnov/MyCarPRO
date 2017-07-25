package vstoyanov.mycar.mycarpro.registerscreen;

import vstoyanov.mycar.mycarpro.data.Constants;
import vstoyanov.mycar.mycarpro.data.model.User;
import vstoyanov.mycar.mycarpro.data.repository.user.UserRepository;
import vstoyanov.mycar.mycarpro.util.StringUtils;


public class RegisterPresenter implements RegisterContract.Presenter{


    private RegisterContract.View mView;
    private UserRepository mUserRepository;

    public RegisterPresenter(RegisterContract.View mView, UserRepository mUserRepository) {
        this.mView = mView;
        this.mUserRepository = mUserRepository;
    }

    @Override
    public void start() {

    }

    @Override
    public void register(String firstName, String lastName, String email, String password) {
        mView.showAuthenticating();

        User user = new User(firstName, lastName, email, password);

        if (validate(user)) {
            mUserRepository.saveUser(user, new UserRepository.OnUserSignCallback() {
                @Override
                public void onSuccess(String email) {
                    mView.hideAuthenticating();
                    mView.showRegisterSucceeded(email);
                    mView.continueToTheApp();
                }

                @Override
                public void onFailure() {
                    mView.hideAuthenticating();
                    mView.showRegisterFailed();
                }
            });
        }else {
            mView.hideAuthenticating();
        }
    }

    private boolean validate(User user) {
        boolean valid = true;

        if (!StringUtils.checkNotNullOrEmpty(user.getFirstName())) {
            valid = false;
            mView.showFirstNameError("Invalid email");
        }

        if (!StringUtils.checkNotNullOrEmpty(user.getPassword())) {
            valid = false;
            mView.showPasswordError("Invalid password");
        }else if (user.getPassword().length() < Constants.REQUIRED_PASSWORD_LENGTH) {
            valid = false;
            mView.showPasswordError("Password must have at least " + Constants.REQUIRED_PASSWORD_LENGTH + "characters");
        }

        if (!StringUtils.checkNotNullOrEmpty(user.getEmail())) {
            valid = false;
            mView.showEmailError("Invalid email");
        }

        if (!StringUtils.checkNotNullOrEmpty(user.getLastName())) {
            valid = false;
            mView.showLastNameError("Invalid last name");
        }

        return valid;
    }
}
