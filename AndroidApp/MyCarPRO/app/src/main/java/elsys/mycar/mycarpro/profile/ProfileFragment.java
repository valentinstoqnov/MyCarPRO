package elsys.mycar.mycarpro.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.base.Preconditions;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import elsys.mycar.mycarpro.R;

public class ProfileFragment extends Fragment implements ProfileContract.View{

    public static final String TAG = "ProfileFragment";

    @BindView(R.id.tv_profile_first_name) TextView tvFirstName;
    @BindView(R.id.tv_profile_last_name) TextView tvLastName;
    @BindView(R.id.tv_profile_email) TextView tvEmail;
    @BindView(R.id.pb_profile) ProgressBar progressBar;

    private Unbinder mUnbinder;
    private ProfileContract.Presenter mPresenter;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_profile, menu);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                mPresenter.openSettings();
                return true;
            case R.id.action_edit_profile:
                mPresenter.openEditProfile();
                return true;
            case R.id.action_sign_out:
                mPresenter.signOutCurrentUser();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showProgress() {
        progressBar.setIndeterminate(true);
    }

    @Override
    public void hideProgress() {
        progressBar.setIndeterminate(false);
    }

    @Override
    public void showFailedToFindSuchUser() {
        Toast.makeText(getContext(), R.string.service_retrieval_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setEmail(String email) {
        tvEmail.setText(email);
    }

    @Override
    public void setFirstName(String firstName) {
        tvFirstName.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        tvLastName.setText(lastName);
    }

    @Override
    public void showSettingsUi() {
        // TODO: 13.06.17  
    }

    @Override
    public void showEditProfileUi(String userId) {
        Toast.makeText(getContext(), userId, Toast.LENGTH_LONG).show();
        // TODO: 13.06.17
    }
}
