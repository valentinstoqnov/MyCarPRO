package elsys.mycar.mycarpro.registerscreen;

import elsys.mycar.mycarpro.data.repository.user.UserRepository;

/**
 * Created by valio_stoyanov on 02.05.17.
 */

public class RegisterPresenter


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

        String id = UUID.randomUUID().toString();
        User user = new User(id, firstName, lastName, email, password);

        if (validate(user)) {
            if (mUserRepository.exist(user)) {
                mView.showAccountExistsError();
            }else {
                mUserRepository.save(user);
            }
        }

        mView.hideAuthenticating();
        mView.registered(email);
    }

    private boolean validate(User user) {
        boolean valid = true;

        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            valid = false;
            mView.showFirstNameError("Invalid email");
        }

        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            valid = false;
            mView.showLastNameError("Invalid password");
        }

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
