package mycar.elsys.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mycar.elsys.login.LoginActivity;
import mycar.elsys.realm.models.User;
import mycar.elsys.utils.TextInputUtils;
import mycar.elsys.base.activity.BaseActivity;
import registration.elsys.R;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.til_register_first_name) TextInputLayout tilFirstName;
    @BindView(R.id.til_register_last_name) TextInputLayout tilLastName;
    @BindView(R.id.til_register_password) TextInputLayout tilPassword;
    @BindView(R.id.til_register_confirm_password) TextInputLayout tilConfirmPassword;
    @BindView(R.id.til_register_email) TextInputLayout tilEmail;
    @BindView(R.id.btn_register) Button btnRegister;
    @BindView(R.id.tv_register_login) TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_register)
    public void registerClick() {
        //register the user -> if success -> set result -> login MAIL verifycation !?!?!
        String firstName = TextInputUtils.getTextFromTil(tilFirstName);
        String lastName = TextInputUtils.getTextFromTil(tilLastName);
        String password = TextInputUtils.getTextFromTil(tilPassword);
        String confirmPassword = TextInputUtils.getTextFromTil(tilConfirmPassword);
        String email = TextInputUtils.getTextFromTil(tilEmail);
        User user = new User(firstName, lastName, password, email);
    }

    @OnClick(R.id.tv_register_login)
    public void goToLoginActivity() {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }
}
