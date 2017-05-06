package elsys.mycar.mycarpro.registerscreen;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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
import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.loginscreen.LoginActivity;
import elsys.mycar.mycarpro.util.TextInputUtils;

public class RegisterFragment extends Fragment implements RegisterContract.View{


    public static final String TAG = "RegisterFragment";

    public static final String REGISTER_RESULT_USERNAME = "REGISTER_USERNAME";

    @BindView(R.id.til_register_username) TextInputLayout tilUsername;
    @BindView(R.id.til_register_first_name) TextInputLayout tilFirstName;
    @BindView(R.id.til_register_last_name) TextInputLayout tilLastName;
    @BindView(R.id.til_register_email) TextInputLayout tilEmail;
    @BindView(R.id.til_register_password) TextInputLayout tilPassword;

    @BindString(R.string.authenticating) String authenticating;

    private ProgressDialog mProgressDialog;
    private Unbinder mUnbinder;
    private RegisterContract.Presenter mPresenter;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setIndeterminate(true);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @OnClick(R.id.tv_register_to_login)
    public void onAlreadyLoginClick() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @OnClick(R.id.btn_register)
    public void onRegisterButtonClick() {
        String username = TextInputUtils.getTextFromTil(tilUsername);
        String firstName = TextInputUtils.getTextFromTil(tilFirstName);
        String lastName = TextInputUtils.getTextFromTil(tilLastName);
        String email = TextInputUtils.getTextFromTil(tilEmail);
        String password = TextInputUtils.getTextFromTil(tilPassword);

        mPresenter.register(username, firstName, lastName, email, password);
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        this.mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showUserNameError(String error) {
        tilUsername.setError(error);
    }

    @Override
    public void showFirstNameError(String error) {
        tilFirstName.setError(error);
    }

    @Override
    public void showLastNameError(String error) {
        tilLastName.setError(error);
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
    public void showAuthenticating() {
        mProgressDialog.show();
    }

    @Override
    public void hideAuthenticating() {
        mProgressDialog.dismiss();
    }

    @Override
    public void showRegisterFailed() {
        Toast.makeText(getContext(), "Registration failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registered(String username) {
        Toast.makeText(getContext(), "Registered!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra(REGISTER_RESULT_USERNAME, username);
        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }
}
