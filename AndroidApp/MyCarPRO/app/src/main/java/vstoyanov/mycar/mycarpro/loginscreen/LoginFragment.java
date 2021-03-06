package vstoyanov.mycar.mycarpro.loginscreen;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.common.base.Preconditions;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vstoyanov.mycar.mycarpro.R;
import vstoyanov.mycar.mycarpro.homescreen.MainActivity;
import vstoyanov.mycar.mycarpro.registerscreen.RegisterActivity;
import vstoyanov.mycar.mycarpro.registerscreen.RegisterFragment;
import vstoyanov.mycar.mycarpro.util.TextInputUtils;

public class LoginFragment extends Fragment implements LoginContract.View {

    public static final String TAG = "LoginFragment";

    @BindView(R.id.til_login_email) TextInputLayout tilEmail;
    @BindView(R.id.til_login_password) TextInputLayout tilPassword;
    @BindString(R.string.signing_in) String signingIn;
    @BindString(R.string.login_greeting) String loginGreeting;

    private LoginContract.Presenter mPresenter;
    private Unbinder mUnbinder;
    private ProgressDialog mProgressDialog;

    public static final int REGISTER_REQUEST_CODE = 13;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setMessage(signingIn);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @OnClick(R.id.btn_login)
    public void onLoginButtonClick() {
        String email = TextInputUtils.getTextFromTil(tilEmail);
        String password = TextInputUtils.getTextFromTil(tilPassword);

        mPresenter.login(email, password);
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
                TextInputUtils.setTextToTil(tilEmail, email);
            }
            Toast.makeText(getContext(), "Now enter your credentials to continue", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.mPresenter = Preconditions.checkNotNull(presenter, "LoginContract.Presenter cannot be null");
    }

    @Override
    public void setEmail(String email) {
        TextInputUtils.setTextToTil(tilEmail, email);
    }

    @Override
    public void showEmailError(String error) {
        tilEmail.setError(error);
    }

    @Override
    public void showPasswordError(String error) {
        tilPassword.setError(error);
    }

    @Override
    public void showProgress() {
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        mProgressDialog.dismiss();
    }

    @Override
    public void showLoginSucceeded(String email) {
        String message = String.format(loginGreeting, email);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void continueToTheApp() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void showLoginFailed() {
        Toast.makeText(getContext(), R.string.login_failed, Toast.LENGTH_LONG).show();
    }
}
