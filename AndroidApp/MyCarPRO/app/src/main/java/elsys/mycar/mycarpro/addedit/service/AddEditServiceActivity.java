package elsys.mycar.mycarpro.addedit.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.data.Constants;
import elsys.mycar.mycarpro.data.repository.activities.service.ServiceRepositoryImpl;
import elsys.mycar.mycarpro.util.ActivityUtils;

public class AddEditServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_service);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        AddEditServiceFragment addEditServiceFragment = (AddEditServiceFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_add_service);

        if (addEditServiceFragment == null) {
            addEditServiceFragment = AddEditServiceFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), addEditServiceFragment, R.id.frame_layout_add_service);
        }

        Intent intent = getIntent();
        String vehicleId = intent.getStringExtra(Constants.VEHICLE_ID);
        String serviceId = intent.getStringExtra(Constants.SERVICE_ID);

        AddEditServicePresenter addEditServicePresenter = new AddEditServicePresenter(vehicleId, serviceId, new ServiceRepositoryImpl(), addEditServiceFragment, true);

        addEditServiceFragment.setPresenter(addEditServicePresenter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
