package elsys.mycar.mycarpro.loginscreen;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import elsys.mycar.mycarpro.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment {

    @BindView(R.id.til_login_email) TextInputLayout tilEmail;
    @BindView(R.id.til_login_password) TextInputLayout tilPassword;
    private ProgressBar progressBar;

    private LoginContract.Presenter mPresenter;
    private Unbinder mUnbinder;

    public static final int REGISTER_REQUEST_CODE = 12;

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
            String email = data.getStringExtra(RegisterFragment.REGISTER_RESULT_EMAIL);
            if (email != null) {
                TextInputUtils.setTextToTil(tilEmail, email);
            }
            AlertUtils.showMessage("Now enter your credentials to continue", getActivity());
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
        progressBar.setIndeterminate(true);
    }

    @Override
    public void hideProgress() {
        progressBar.setIndeterminate(false);
    }

    @Override
    public void showNoSuchUser() {
        AlertUtils.showError("User not found", "Do you have registration?", null, getActivity());
    }

    @Override
    public void loggedIn() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
