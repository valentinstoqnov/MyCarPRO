package elsys.mycar.mycarpro.addedit.refueling;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.homescreen.MainActivity;
import elsys.mycar.mycarpro.util.ActivityUtils;
import elsys.mycar.mycarpro.util.AuthenticationUtils;
import elsys.mycar.mycarpro.util.ProviderUtils;

public class AddEditRefuelingActivity extends AppCompatActivity {

    private AuthenticationUtils mAuthenticationUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuthenticationUtils = new AuthenticationUtils(this);
        setContentView(R.layout.activity_add_edit_refueling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AddEditRefuelingFragment addEditRefuelingFragment = (AddEditRefuelingFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_add_refueling);

        if (addEditRefuelingFragment == null) {
            addEditRefuelingFragment = AddEditRefuelingFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), addEditRefuelingFragment, R.id.frame_layout_add_refueling);
        }

        String vehicleId = getIntent().getStringExtra(MainActivity.VEHICLE_ID);

        String token = mAuthenticationUtils.getToken();
        AddEditRefuelingPresenter addEditRefuelingPresenter = new AddEditRefuelingPresenter(vehicleId, null, ProviderUtils.getRefuelingRepository(token), addEditRefuelingFragment, ProviderUtils.getVehicleRepository(token), true);

        addEditRefuelingFragment.setPresenter(addEditRefuelingPresenter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAuthenticationUtils.checkUser();
    }
}
