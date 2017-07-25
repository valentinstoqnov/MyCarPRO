package vstoyanov.mycar.mycarpro.addedit.vehicle;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.google.common.base.Preconditions;

import butterknife.ButterKnife;
import vstoyanov.mycar.mycarpro.R;
import vstoyanov.mycar.mycarpro.base.FirebaseAuthBaseActivity;
import vstoyanov.mycar.mycarpro.data.Constants;
import vstoyanov.mycar.mycarpro.data.repository.vehicle.VehicleRepositoryImpl;
import vstoyanov.mycar.mycarpro.util.ActivityUtils;

public class AddEditVehicleActivity extends FirebaseAuthBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_vehicle);
        setUpToolbar();

        String vehicleId = getIntent().getStringExtra(Constants.VEHICLE_ID);

        AddEditVehicleFragment addEditVehicleFragment = AddEditVehicleFragment.newInstance();
        AddEditVehiclePresenter addEditVehiclePresenter = new AddEditVehiclePresenter(getCurrentUserId(), vehicleId, new VehicleRepositoryImpl(), addEditVehicleFragment, true);
        addEditVehicleFragment.setPresenter(addEditVehiclePresenter);

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), addEditVehicleFragment, R.id.frame_layout_add_vehicle);
    }

    private void setUpToolbar() {
        Toolbar toolbar = ButterKnife.findById(this, R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = Preconditions.checkNotNull(getSupportActionBar());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
