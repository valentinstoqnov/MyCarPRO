package vstoyanov.mycar.mycarpro.addedit.refueling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import vstoyanov.mycar.mycarpro.R;
import vstoyanov.mycar.mycarpro.base.FirebaseAuthBaseActivity;
import vstoyanov.mycar.mycarpro.data.Constants;
import vstoyanov.mycar.mycarpro.data.repository.activities.refueling.RefuelingRepositoryImpl;
import vstoyanov.mycar.mycarpro.util.ActivityUtils;

public class AddEditRefuelingActivity extends FirebaseAuthBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_refueling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        AddEditRefuelingFragment addEditRefuelingFragment = (AddEditRefuelingFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_add_refueling);

        if (addEditRefuelingFragment == null) {
            addEditRefuelingFragment = AddEditRefuelingFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), addEditRefuelingFragment, R.id.frame_layout_add_refueling);
        }

        Intent intent = getIntent();
        String vehicleId = intent.getStringExtra(Constants.VEHICLE_ID);
        String refuelingId = intent.getStringExtra(Constants.REFUELING_ID);

        AddEditRefuelingPresenter addEditRefuelingPresenter = new AddEditRefuelingPresenter(vehicleId, refuelingId, new RefuelingRepositoryImpl(), addEditRefuelingFragment, true);

        addEditRefuelingFragment.setPresenter(addEditRefuelingPresenter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
