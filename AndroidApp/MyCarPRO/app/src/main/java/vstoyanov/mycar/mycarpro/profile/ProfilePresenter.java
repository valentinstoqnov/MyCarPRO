package vstoyanov.mycar.mycarpro.profile;

import com.google.common.base.Preconditions;

import vstoyanov.mycar.mycarpro.data.model.User;
import vstoyanov.mycar.mycarpro.data.repository.OnItemFetchedCallback;
import vstoyanov.mycar.mycarpro.data.repository.user.UserRepository;

public class ProfilePresenter implements ProfileContract.Presenter{

    private String mUserId;
    private ProfileContract.View mView;
    private UserRepository mUserRepository;
    private boolean mIsDataMissing;

    public ProfilePresenter(String userId, ProfileContract.View view, UserRepository userRepository, boolean isDataMissing) {
        this.mUserId = Preconditions.checkNotNull(userId);
        this.mView = Preconditions.checkNotNull(view);
        this.mUserRepository = Preconditions.checkNotNull(userRepository);
        this.mIsDataMissing = isDataMissing;
    }

    @Override
    public void start() {
        if (mIsDataMissing) {
            mView.showProgress();
            mUserRepository.fetchUserById(mUserId, new OnItemFetchedCallback<User>() {
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
    public void openSettings() {
        mView.showSettingsUi();
    }

    @Override
    public void openEditProfile() {
        mView.showEditProfileUi(mUserId);
    }

    @Override
    public void signOutCurrentUser() {
        mUserRepository.signOutCurrentUser();
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }

    private void populateUser(User user) {
        mView.setEmail(user.getEmail());
        mView.setFirstName(user.getFirstName());
        mView.setLastName(user.getLastName());
        mIsDataMissing = false;
    }
}
