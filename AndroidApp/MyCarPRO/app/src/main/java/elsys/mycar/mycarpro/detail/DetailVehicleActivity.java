package elsys.mycar.mycarpro.detail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.data.VehicleRepositoryImpl;
import elsys.mycar.mycarpro.util.ActivityUtils;

public class DetailVehicleActivity extends AppCompatActivity {

    public static final String DETAIL_VEHICLE_ID = "VEHICLE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_vehicle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        ImageView imageView = (ImageView) findViewById(R.id.img_view_detail_vehicle);

        DetailVehicleFragment detailVehicleFragment = DetailVehicleFragment.newInstance();

        DetailVehiclePresenter detailVehiclePresenter = new DetailVehiclePresenter(
                detailVehicleFragment,
                VehicleRepositoryImpl.getInstance(),
                getIntent().getStringExtra(DETAIL_VEHICLE_ID),
                true
        );

        detailVehicleFragment.setPresenter(detailVehiclePresenter);

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), detailVehicleFragment, R.id.frame_layout_detail_vehicle);
    }
}
