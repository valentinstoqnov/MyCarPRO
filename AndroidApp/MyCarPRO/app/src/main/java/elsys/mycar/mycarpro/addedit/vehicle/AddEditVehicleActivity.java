package elsys.mycar.mycarpro.addedit.vehicle;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.common.base.Preconditions;

import butterknife.ButterKnife;
import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepositoryImpl;
import elsys.mycar.mycarpro.homescreen.MainActivity;
import elsys.mycar.mycarpro.util.ActivityUtils;

public class AddEditVehicleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_vehicle);
        setUpToolbar();

        String vehicleId = getIntent().getStringExtra(MainActivity.VEHICLE_ID);

        AddEditVehicleFragment addEditVehicleFragment = AddEditVehicleFragment.newInstance();
        AddEditVehiclePresenter addEditVehiclePresenter = new AddEditVehiclePresenter(vehicleId, new VehicleRepositoryImpl(), addEditVehicleFragment, true);
        addEditVehicleFragment.setPresenter(addEditVehiclePresenter);

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                addEditVehicleFragment,
                R.id.frame_layout_add_vehicle);
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
