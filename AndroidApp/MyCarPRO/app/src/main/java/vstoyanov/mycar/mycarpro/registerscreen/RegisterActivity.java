package vstoyanov.mycar.mycarpro.registerscreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import vstoyanov.mycar.mycarpro.R;
import vstoyanov.mycar.mycarpro.data.repository.user.UserRepositoryImpl;
import vstoyanov.mycar.mycarpro.util.FragmentManagingUtils;

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

        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        RegisterPresenter registerPresenter = new RegisterPresenter(registerFragment,userRepository);
        registerFragment.setPresenter(registerPresenter);
    }

}
