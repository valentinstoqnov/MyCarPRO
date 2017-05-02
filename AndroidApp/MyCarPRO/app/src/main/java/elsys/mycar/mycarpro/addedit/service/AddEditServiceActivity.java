package elsys.mycar.mycarpro.addedit.service;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.addedit.insurance.AddEditInsuranceFragment;
import elsys.mycar.mycarpro.data.VehicleRepositoryImpl;
import elsys.mycar.mycarpro.util.ActivityUtils;
import elsys.mycar.mycarpro.util.ProviderUtils;
import elsys.mycar.mycarpro.util.StringUtils;
import elsys.mycar.mycarpro.util.TokenUtils;

import static elsys.mycar.mycarpro.homescreen.HomeActivity.VEHICLE_ID;

public class AddEditServiceActivity extends AppCompatActivity {

    private TokenUtils mTokenUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTokenUtils = new TokenUtils(this);
        setContentView(R.layout.activity_add_edit_service);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AddEditServiceFragment addEditServiceFragment = (AddEditServiceFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_add_service);

        if (addEditServiceFragment == null) {
            addEditServiceFragment = AddEditServiceFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), addEditServiceFragment, R.id.frame_layout_add_service);
        }

        String vehicleId = getIntent().getStringExtra(VEHICLE_ID);

        AddEditServicePresenter addEditServicePresenter = new AddEditServicePresenter(vehicleId, null, ProviderUtils.getVehicleRepository(mTokenUtils.getToken()), addEditServiceFragment, true);

        addEditServiceFragment.setPresenter(addEditServicePresenter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTokenUtils.checkToken();
    }
}
