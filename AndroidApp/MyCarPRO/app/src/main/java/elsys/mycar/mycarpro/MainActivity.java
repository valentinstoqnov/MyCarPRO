package elsys.mycar.mycarpro;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import butterknife.ButterKnife;
import butterknife.OnClick;
import elsys.mycar.mycarpro.addedit.insurance.AddEditInsuranceActivity;
import elsys.mycar.mycarpro.addedit.refueling.AddEditRefuelingActivity;
import elsys.mycar.mycarpro.addedit.service.AddEditServiceActivity;
import elsys.mycar.mycarpro.addedit.vehicle.AddEditVehicleActivity;
import elsys.mycar.mycarpro.data.VehicleRepositoryImpl;
import elsys.mycar.mycarpro.list.ListVehicleFragment;
import elsys.mycar.mycarpro.list.ListVehiclePresenter;
import elsys.mycar.mycarpro.util.ActivityUtils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @OnClick({R.id.fab_main_car, R.id.fab_main_service, R.id.fab_main_insurance, R.id.fab_main_refueling})
    public void onFabMenuClick(FloatingActionButton fab) {
        Class aClass = null;
        switch (fab.getId()) {
            case R.id.fab_main_car:
                aClass = AddEditVehicleActivity.class;
                break;
            case R.id.fab_main_service:
                aClass = AddEditServiceActivity.class;
                break;
            case R.id.fab_main_insurance:
                aClass = AddEditInsuranceActivity.class;
                break;
            case R.id.fab_main_refueling:
                aClass = AddEditRefuelingActivity.class;
                break;
        }
        if (aClass != null) {
            Intent intent = new Intent(this, aClass);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_vehicles) {
            ListVehicleFragment listVehicleFragment = ListVehicleFragment.newInstance();
            ListVehiclePresenter listVehiclePresenter = new ListVehiclePresenter(VehicleRepositoryImpl.getInstance(), listVehicleFragment);
            listVehicleFragment.setPresenter(listVehiclePresenter);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), listVehicleFragment, R.id.frame_layout_main_content);
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
