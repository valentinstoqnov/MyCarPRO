package elsys.mycar.mycarpro.addedit.service;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.homescreen.MainActivity;
import elsys.mycar.mycarpro.util.ActivityUtils;
import elsys.mycar.mycarpro.util.AuthenticationUtils;
import elsys.mycar.mycarpro.util.ProviderUtils;

public class AddEditServiceActivity extends AppCompatActivity {

    private AuthenticationUtils mAuthenticationUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuthenticationUtils = new AuthenticationUtils(this);
        setContentView(R.layout.activity_add_edit_service);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AddEditServiceFragment addEditServiceFragment = (AddEditServiceFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_add_service);

        if (addEditServiceFragment == null) {
            addEditServiceFragment = AddEditServiceFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), addEditServiceFragment, R.id.frame_layout_add_service);
        }

        String vehicleId = getIntent().getStringExtra(MainActivity.VEHICLE_ID);
        System.out.println("add edit service vehicle id = " + vehicleId);

        String token = mAuthenticationUtils.getToken();
        AddEditServicePresenter addEditServicePresenter = new AddEditServicePresenter(vehicleId, null, ProviderUtils.getServiceRepository(token), ProviderUtils.getVehicleRepository(token), addEditServiceFragment, true);

        addEditServiceFragment.setPresenter(addEditServicePresenter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAuthenticationUtils.checkUser();
    }
}
