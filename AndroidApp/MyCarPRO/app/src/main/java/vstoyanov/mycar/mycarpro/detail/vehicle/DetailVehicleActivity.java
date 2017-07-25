package vstoyanov.mycar.mycarpro.detail.vehicle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import vstoyanov.mycar.mycarpro.R;
import vstoyanov.mycar.mycarpro.data.Constants;
import vstoyanov.mycar.mycarpro.data.repository.vehicle.VehicleRepositoryImpl;
import vstoyanov.mycar.mycarpro.util.ActivityUtils;

public class DetailVehicleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_vehicle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DetailVehicleFragment detailVehicleFragment = (DetailVehicleFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_detail_vehicle);

        if (detailVehicleFragment == null) {
            detailVehicleFragment = DetailVehicleFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), detailVehicleFragment, R.id.frame_layout_detail_vehicle);
        }

        String vehicleId = getIntent().getStringExtra(Constants.VEHICLE_ID);
        System.out.println("DetailVehicleActivity vehicle id: " + vehicleId);
        DetailVehiclePresenter detailVehiclePresenter = new DetailVehiclePresenter(
                detailVehicleFragment, new VehicleRepositoryImpl(),
                vehicleId, true
        );

        detailVehicleFragment.setPresenter(detailVehiclePresenter);
    }
}
