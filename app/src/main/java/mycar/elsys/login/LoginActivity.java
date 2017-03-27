package mycar.elsys.login;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mycar.elsys.utils.TextInputUtils;
import mycar.elsys.register.RegisterActivity;
import mycar.elsys.base.activity.BaseActivity;
import registration.elsys.R;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.til_login_email) TextInputLayout tilEmail;
    @BindView(R.id.til_login_password) TextInputLayout tilPassword;
    @BindView(R.id.btn_login) Button btnLogin;
    @BindView(R.id.btn_login_facebook) Button btnFbLogin;
    @BindView(R.id.tv_login_register) TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void loginClick() {
        String email = TextInputUtils.getTextFromTil(tilEmail);
        String password = TextInputUtils.getTextFromTil(tilPassword);
    }

    @OnClick(R.id.btn_login_facebook)
    public void facebookLoginClick() {}

    @OnClick(R.id.tv_login_register)
    public void goToRegisterActivity() {
        //for result?
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
}
