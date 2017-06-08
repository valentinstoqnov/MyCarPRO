package elsys.mycar.mycarpro.data.repository.user;

import elsys.mycar.mycarpro.data.model.User;

public interface UserRepository {

    void saveUser(User user, OnUserSignCallback callback);

    void loginUser(String username, String password, OnUserSignCallback callback);


    interface OnUserSignCallback {

        void onSuccess(String email);

        void onFailure();
    }
}
