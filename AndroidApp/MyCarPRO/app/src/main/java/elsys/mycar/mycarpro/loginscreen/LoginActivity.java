package elsys.mycar.mycarpro.loginscreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.util.FragmentManagingUtils;
import elsys.mycar.mycarpro.util.ProviderUtils;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FragmentManagingUtils fragmentManagingUtils = new FragmentManagingUtils(getSupportFragmentManager(), R.id.frame_layout_login);
        LoginFragment loginFragment = (LoginFragment) fragmentManagingUtils.getFragmentByTag(LoginFragment.TAG);

        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance();
            fragmentManagingUtils.addFragment(loginFragment, LoginFragment.TAG);
        }else {
            fragmentManagingUtils.showFragment(loginFragment);
        }

        LoginPresenter loginPresenter = new LoginPresenter(loginFragment, ProviderUtils.getUserRepository());

        loginFragment.setPresenter(loginPresenter);
    }

}
