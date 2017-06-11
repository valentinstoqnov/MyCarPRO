package elsys.mycar.mycarpro.addedit.insurance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.data.Constants;
import elsys.mycar.mycarpro.data.repository.activities.insurance.InsuranceRepositoryImpl;
import elsys.mycar.mycarpro.homescreen.MainActivity;
import elsys.mycar.mycarpro.util.ActivityUtils;

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

        String vehicleId = getIntent().getStringExtra(Constants.VEHICLE_ID);
        String insuranceId = getIntent().getStringExtra(Constants.INSURANCE_ID);

        AddEditInsurancePresenter addEditInsurancePresenter = new AddEditInsurancePresenter(vehicleId, insuranceId, new InsuranceRepositoryImpl(), addEditInsuranceFragment, true);

        addEditInsuranceFragment.setPresenter(addEditInsurancePresenter);
    }
}
