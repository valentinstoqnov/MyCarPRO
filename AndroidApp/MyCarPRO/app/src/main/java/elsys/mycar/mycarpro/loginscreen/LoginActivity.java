package elsys.mycar.mycarpro.loginscreen;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.data.api.UserApi;
import elsys.mycar.mycarpro.data.repository.user.UserRepositoryImpl;
import elsys.mycar.mycarpro.util.FragmentManagingUtils;
import elsys.mycar.mycarpro.util.ProviderUtils;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
