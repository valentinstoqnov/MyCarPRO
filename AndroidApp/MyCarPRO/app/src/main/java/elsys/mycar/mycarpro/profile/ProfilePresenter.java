package elsys.mycar.mycarpro.profile;

import elsys.mycar.mycarpro.data.model.User;
import elsys.mycar.mycarpro.data.repository.OnItemFetchedCallback;
import elsys.mycar.mycarpro.data.repository.user.UserRepository;

public class ProfilePresenter implements ProfileContract.Presenter{

    private ProfileContract.View mView;
    private UserRepository mUserRepository;
    private boolean mIsDataMissing;

    @Override
    public void start() {
        if (mIsDataMissing) {
            mView.showProgress();
            mUserRepository.fetchUserById("", new OnItemFetchedCallback<User>() {
                @Override
                public void onSuccess(User item) {
                    populateUser(item);
                    mView.hideProgress();
                }

                @Override
                public void onFailure() {
                    mView.hideProgress();
                    mView.showFailedToFindSuchUser();
                }
            });
        }
    }

    @Override
    public void signOutCurrentUser() {

    }

    @Override
    public void editAccount(String userId) {

    }

    private void populateUser(User user) {

        mIsDataMissing = false;
    }
}
