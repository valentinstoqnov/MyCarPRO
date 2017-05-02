package elsys.mycar.mycarpro.loginscreen;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.common.base.Preconditions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.homescreen.HomeActivity;
import elsys.mycar.mycarpro.registerscreen.RegisterActivity;
import elsys.mycar.mycarpro.registerscreen.RegisterFragment;
import elsys.mycar.mycarpro.util.TextInputUtils;
import elsys.mycar.mycarpro.util.TokenUtils;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment implements LoginContract.View {

    public static final String TAG = "LoginFragment";

    @BindView(R.id.til_login_username) TextInputLayout tilUsername;
    @BindView(R.id.til_login_password) TextInputLayout tilPassword;
    private ProgressBar progressBar;

    private LoginContract.Presenter mPresenter;
    private Unbinder mUnbinder;

    public static final int REGISTER_REQUEST_CODE = 13;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressBar = (ProgressBar) getActivity().findViewById(R.id.pb_login);
    }

    @OnClick(R.id.btn_login)
    public void onLoginButtonClick() {
        String username = TextInputUtils.getTextFromTil(tilUsername);
        String password = TextInputUtils.getTextFromTil(tilPassword);

        Log.d("asdad", username);
        mPresenter.login(username, password);
    }

    @OnClick(R.id.tv_login_to_register)
    public void onNoAccountYetClick() {
        Intent intent = new Intent(getActivity(), RegisterActivity.class);
        startActivityForResult(intent, REGISTER_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REGISTER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            String email = data.getStringExtra(RegisterFragment.REGISTER_RESULT_USERNAME);
            if (email != null) {
                TextInputUtils.setTextToTil(tilUsername, email);
            }
            Toast.makeText(getContext(), "Now enter your credentials to continue", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.mPresenter = Preconditions.checkNotNull(presenter, "LoginContract.Presenter cannot be null");
    }

    @Override
    public void setUsername(String username) {
        TextInputUtils.setTextToTil(tilUsername, username);
    }

    @Override
    public void showUsernameError(String error) {
        tilUsername.setError(error);
    }

    @Override
    public void showPasswordError(String error) {
        tilPassword.setError(error);
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
    public void showLoginFailed() {
        Toast.makeText(getContext(), "Login failed, are you registered?", Toast.LENGTH_LONG).show();
    }

    @Override
    public void loggedIn(String token) {
        Log.d("on logged in", "token = " + token);
        System.out.println("on logged in token = " + token);
        new TokenUtils(getActivity()).saveToken(token);
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
