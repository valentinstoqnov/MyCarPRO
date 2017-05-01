package elsys.mycar.mycarpro.addedit.insurance;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;

import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.data.VehicleRepositoryImpl;
import elsys.mycar.mycarpro.util.ActivityUtils;

import static elsys.mycar.mycarpro.homescreen.HomeActivity.VEHICLE_ID;

public class AddEditInsuranceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_insurance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AddEditInsuranceFragment addEditInsuranceFragment = (AddEditInsuranceFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_add_insurance);

        if(addEditInsuranceFragment == null) {
            addEditInsuranceFragment = AddEditInsuranceFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), addEditInsuranceFragment, R.id.frame_layout_add_insurance);
        }

        String vehicleId = getIntent().getStringExtra(VEHICLE_ID);

        AddEditInsurancePresenter addEditInsurancePresenter = new AddEditInsurancePresenter(vehicleId, null, VehicleRepositoryImpl.getInstance(), addEditInsuranceFragment, true);

        addEditInsuranceFragment.setPresenter(addEditInsurancePresenter);
    }

}
