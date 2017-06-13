package elsys.mycar.mycarpro.profile;

import android.util.Log;

import com.google.common.base.Preconditions;

import elsys.mycar.mycarpro.data.model.User;
import elsys.mycar.mycarpro.data.repository.OnItemFetchedCallback;
import elsys.mycar.mycarpro.data.repository.user.UserRepository;

public class ProfilePresenter implements ProfileContract.Presenter{

    private String userId;
    private ProfileContract.View mView;
    private UserRepository mUserRepository;
    private boolean mIsDataMissing;

    public ProfilePresenter(String userId, ProfileContract.View mView, UserRepository mUserRepository, boolean mIsDataMissing) {
        this.userId = userId;
        this.mView = Preconditions.checkNotNull(mView);
        this.mUserRepository = Preconditions.checkNotNull(mUserRepository);
        this.mIsDataMissing = mIsDataMissing;
    }

    @Override
    public void start() {
        if (mIsDataMissing) {
            if (userId != null) {
                mView.showProgress();
                mUserRepository.fetchUserById(userId, new OnItemFetchedCallback<User>() {
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
            }else {
                Log.d("ProfilePresenter:", "User id is null");
                mView.showFailedToFindSuchUser();
            }
        }
    }

    @Override
    public void openSettings() {
        mView.showSettingsUi();
    }

    @Override
    public void openEditProfile() {
        mView.showEditProfileUi(userId);
    }

    @Override
    public void signOutCurrentUser() {
        mUserRepository.signOutCurrentUser();
    }

    private void populateUser(User user) {
        mView.setEmail(user.getEmail());
        mView.setFirstName(user.getFirstName());
        mView.setLastName(user.getLastName());
        mIsDataMissing = false;
    }
}
