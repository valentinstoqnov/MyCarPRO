package elsys.mycar.mycarpro;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindColor;
import butterknife.BindView;
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

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_bar_main) BottomBar bottomBar;
    @BindView(R.id.fab_main) FloatingActionButton fab;
    @BindView(R.id.fab_menu_main) FloatingActionMenu fabMenu;
    @BindView(R.id.spn_main_vehicles) Spinner spinner;
    @BindColor(R.color.colorDarkVehicleTabSelected) int vehicleDarkTabColor;
    @BindColor(R.color.colorDarkActivitiesTabSelected) int activitiesDarkTabColor;
    @BindColor(R.color.colorDarkStatisticsTabSelected) int statisticsDarkTabColor;
    @BindColor(R.color.colorDarkProfileTabSelected) int profileDarkTabColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        setUpBottomBar();
        setUpSpinner();
    }

    @OnClick({R.id.fab_main, R.id.fab_main_insurance, R.id.fab_main_refueling, R.id.fab_main_service})
    public void onFabClick(FloatingActionButton button) {
        Class aClass = null;

        switch (button.getId()) {
            case R.id.fab_main:
                aClass = AddEditVehicleActivity.class;
                break;
            case R.id.fab_main_insurance:
                aClass = AddEditInsuranceActivity.class;
                break;
            case R.id.fab_main_refueling:
                aClass = AddEditRefuelingActivity.class;
                break;
            case R.id.fab_main_service:
                aClass = AddEditServiceActivity.class;
                break;
        }

        if (aClass != null) {
            Intent intent = new Intent(this, aClass);
            startActivity(intent);
        }
    }

    private void setUpSpinner() {
        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this,
                R.layout.vehicles_spinner_item_activities,
                new ArrayList<String>() {{
                    add("SADASD ASDSA");
                    add("NOOOOO");
                    add("SUUUU");
                }});
        spinner.setAdapter(mAdapter);
    }

    private void setUpBottomBar() {
        bottomBar.selectTabAtPosition(1);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                int actionBarColor = bottomBar.getTabWithId(tabId).getBarColorWhenSelected();
                int statusBarColor = activitiesDarkTabColor;

                switch (tabId) {
                    case R.id.tab_vehicles:
                        onVehiclesTabSelected();
                        statusBarColor = vehicleDarkTabColor;
                        break;
                    case R.id.tab_activities:
                        onActivitiesTabSelected();
                        break;
                    case R.id.tab_statistics:
                        onStatisticsTabSelected();
                        statusBarColor = statisticsDarkTabColor;
                        break;
                    case R.id.tab_user_profile:
                        onProfileTabSelected();
                        statusBarColor = profileDarkTabColor;
                        break;
                }

                setBarsColor(statusBarColor, actionBarColor);
            }
        });
    }

    private void onVehiclesTabSelected() {
        ListVehicleFragment listVehicleFragment = ListVehicleFragment.newInstance();
        ListVehiclePresenter listVehiclePresenter = new ListVehiclePresenter(VehicleRepositoryImpl.getInstance(), listVehicleFragment);
        listVehicleFragment.setPresenter(listVehiclePresenter);
        spinner.setVisibility(View.GONE);
        setFabVisible();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), listVehicleFragment, R.id.frame_layout_main_content);
    }

    private void onActivitiesTabSelected() {
        setFabMenuVisible();
        spinner.setVisibility(View.VISIBLE);
    }

    private void onStatisticsTabSelected() {
        hideFabs();
        spinner.setVisibility(View.VISIBLE);
    }

    private void onProfileTabSelected() {
        hideFabs();
        spinner.setVisibility(View.GONE);
    }

    private void setFabVisible() {
        fabMenu.hideMenu(false);
        fab.show(true);
    }

    private void setFabMenuVisible() {
        spinner.setVisibility(View.VISIBLE);
        fab.hide(false);
        fabMenu.showMenu(true);
    }

    private void hideFabs() {
        fabMenu.hideMenu(true);
        fab.hide(true);
    }

    private void setBarsColor(int statusBarColor, int actionBarColor) {
        getWindow().setStatusBarColor(statusBarColor);
        ColorDrawable colorDrawable = new ColorDrawable(actionBarColor);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(colorDrawable);
        }
        spinner.setPopupBackgroundDrawable(colorDrawable);
    }
}
