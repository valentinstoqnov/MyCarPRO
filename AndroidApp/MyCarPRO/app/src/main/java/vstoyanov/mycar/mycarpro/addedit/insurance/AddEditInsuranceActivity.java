package vstoyanov.mycar.mycarpro.addedit.insurance;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import vstoyanov.mycar.mycarpro.R;
import vstoyanov.mycar.mycarpro.base.FirebaseAuthBaseActivity;
import vstoyanov.mycar.mycarpro.data.Constants;
import vstoyanov.mycar.mycarpro.data.repository.activities.insurance.InsuranceRepositoryImpl;
import vstoyanov.mycar.mycarpro.util.ActivityUtils;

public class AddEditInsuranceActivity extends FirebaseAuthBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_insurance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
