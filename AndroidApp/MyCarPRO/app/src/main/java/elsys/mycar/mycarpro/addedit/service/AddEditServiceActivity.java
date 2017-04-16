package elsys.mycar.mycarpro.addedit.service;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.addedit.insurance.AddEditInsuranceFragment;
import elsys.mycar.mycarpro.util.ActivityUtils;

public class AddEditServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_service);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), new AddEditServiceFragment(), R.id.frame_layout_add_service);
    }

}
