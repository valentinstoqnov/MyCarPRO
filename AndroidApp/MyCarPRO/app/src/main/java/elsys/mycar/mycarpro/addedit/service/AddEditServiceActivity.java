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

import static elsys.mycar.mycarpro.homescreen.MainActivity.VEHICLE_ID;

public class AddEditServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_service);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AddEditServiceFragment addEditServiceFragment = (AddEditServiceFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_add_service);

        if (addEditServiceFragment == null) {
            addEditServiceFragment = AddEditServiceFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), addEditServiceFragment, R.id.frame_layout_add_service);
        }

        String vehicleId = getIntent().getStringExtra(VEHICLE_ID);

        AddEditServicePresenter addEditServicePresenter = new AddEditServicePresenter(vehicleId, null, VehicleRepositoryImpl.getInstance(), addEditServiceFragment, true);

        addEditServiceFragment.setPresenter(addEditServicePresenter);
    }

}
