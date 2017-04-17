package elsys.mycar.mycarpro.addedit.refueling;

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

public class AddEditRefuelingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_refueling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AddEditRefuelingFragment addEditRefuelingFragment = (AddEditRefuelingFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_add_refueling);

        if (addEditRefuelingFragment == null) {
            addEditRefuelingFragment = AddEditRefuelingFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), addEditRefuelingFragment, R.id.frame_layout_add_refueling);
        }

        String vehicleId = getIntent().getStringExtra(VEHICLE_ID);

        AddEditRefuelingPresenter addEditRefuelingPresenter = new AddEditRefuelingPresenter(vehicleId, null, addEditRefuelingFragment, VehicleRepositoryImpl.getInstance(), true);

        addEditRefuelingFragment.setPresenter(addEditRefuelingPresenter);
    }

}
