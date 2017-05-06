package elsys.mycar.mycarpro.registerscreen;

import elsys.mycar.mycarpro.data.repository.user.UserRepository;
import elsys.mycar.mycarpro.data.model.User;
import elsys.mycar.mycarpro.util.StringUtils;


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
    public void register(final String username, String firstName, String lastName, String email, String password) {
        mView.showAuthenticating();

        User user = new User(username, firstName, lastName, email, password);

        if (validate(user)) {
            mUserRepository.saveUser(user, new UserRepository.OnUserSavedCallback() {
                @Override
                public void onSuccess(User user) {
                    mView.hideAuthenticating();
                    mView.registered(user.getUsername());
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
            mView.showLastNameError("Invalid password");
        }

        if (!StringUtils.checkNotNullOrEmpty(user.getEmail())) {
            valid = false;
            mView.showEmailError("Invalid email");
        }

        if (!StringUtils.checkNotNullOrEmpty(user.getUsername())) {
            valid = false;
            mView.showPasswordError("Invalid username");
        }

        if (!StringUtils.checkNotNullOrEmpty(user.getLastName())) {
            valid = false;
            mView.showLastNameError("Invalid last name");
        }

        return valid;
    }
}
