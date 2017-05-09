package elsys.mycar.mycarpro.detail.vehicle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.util.ActivityUtils;
import elsys.mycar.mycarpro.util.AuthenticationUtils;
import elsys.mycar.mycarpro.util.ProviderUtils;

public class DetailVehicleActivity extends AppCompatActivity {

    public static final String DETAIL_VEHICLE_ID = "VEHICLE_ID";
    private AuthenticationUtils mAuthenticationUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuthenticationUtils = new AuthenticationUtils(this);
        if (mAuthenticationUtils.checkUser()) {
            setContentView(R.layout.activity_detail_vehicle);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            DetailVehicleFragment detailVehicleFragment = (DetailVehicleFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_detail_vehicle);

            if (detailVehicleFragment == null) {
                detailVehicleFragment = DetailVehicleFragment.newInstance();
                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), detailVehicleFragment, R.id.frame_layout_detail_vehicle);
            }

            String vehicleId = getIntent().getStringExtra(DETAIL_VEHICLE_ID);

            DetailVehiclePresenter detailVehiclePresenter = new DetailVehiclePresenter(
                    detailVehicleFragment, ProviderUtils.getVehicleRepository(mAuthenticationUtils.getToken()),
                    vehicleId, true
            );

            detailVehicleFragment.setPresenter(detailVehiclePresenter);
        }
    }
}
