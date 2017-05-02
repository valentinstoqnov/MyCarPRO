package elsys.mycar.mycarpro.registerscreen;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.data.repository.user.UserRepositoryImpl;
import elsys.mycar.mycarpro.util.FragmentManagingUtils;
import elsys.mycar.mycarpro.util.ProviderUtils;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManagingUtils fragmentManagingUtils = new FragmentManagingUtils(getSupportFragmentManager(), R.id.frame_layout_register);

        RegisterFragment registerFragment = (RegisterFragment) fragmentManagingUtils.getFragmentByTag(RegisterFragment.TAG);

        if (registerFragment == null) {
            registerFragment = RegisterFragment.newInstance();
            fragmentManagingUtils.addFragment(registerFragment, RegisterFragment.TAG);
        }else {
            fragmentManagingUtils.showFragment(registerFragment);
        }

        RegisterPresenter registerPresenter = new RegisterPresenter(registerFragment, ProviderUtils.getUserRepository());
        registerFragment.setPresenter(registerPresenter);
    }

}
