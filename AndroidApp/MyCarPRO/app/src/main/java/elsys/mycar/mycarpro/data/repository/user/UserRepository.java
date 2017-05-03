package elsys.mycar.mycarpro.data.repository.user;

import elsys.mycar.mycarpro.model.User;

public interface UserRepository {

    void saveUser(User user, OnUserSavedCallback callback);

    void loginUser(String username, String password, OnUserLoggedInCallback callback);

    interface OnUserSavedCallback {

        void onSuccess(User user);

        void onFailure();
    }

    interface OnUserLoggedInCallback {

        void onSuccess(String token);

        void onFailure();
    }
}
