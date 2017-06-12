package elsys.mycar.mycarpro.data.repository.user;

import elsys.mycar.mycarpro.data.model.User;
import elsys.mycar.mycarpro.data.repository.OnItemFetchedCallback;

public interface UserRepository {

    void saveUser(User user, OnUserSignCallback callback);

    void loginUser(String email, String password, OnUserSignCallback callback);

    void fetchUserById(String id, OnItemFetchedCallback<User> callback);

    void signOutCurrentUser();

    interface OnUserSignCallback {

        void onSuccess(String email);

        void onFailure();
    }
}
