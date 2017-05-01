package elsys.mycar.mycarpro.homescreen;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.common.base.Preconditions;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.addedit.insurance.AddEditInsuranceActivity;
import elsys.mycar.mycarpro.addedit.refueling.AddEditRefuelingActivity;
import elsys.mycar.mycarpro.addedit.service.AddEditServiceActivity;
import elsys.mycar.mycarpro.addedit.vehicle.AddEditVehicleActivity;
import elsys.mycar.mycarpro.data.VehicleRepositoryImpl;
import elsys.mycar.mycarpro.list.activities.ActivitiesFragment;
import elsys.mycar.mycarpro.list.vehicles.ListVehicleFragment;
import elsys.mycar.mycarpro.list.vehicles.ListVehiclePresenter;
import elsys.mycar.mycarpro.profile.ProfileFragment;
import elsys.mycar.mycarpro.statistics.StatisticsFragment;
import elsys.mycar.mycarpro.util.ActivityUtils;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    public static final String VEHICLE_ID = "VEHICLE_ID";

    @BindView(R.id.bottom_bar_main) BottomBar bottomBar;
    @BindView(R.id.fab_main) FloatingActionButton fab;
    @BindView(R.id.fab_menu_main) FloatingActionMenu fabMenu;
    @BindView(R.id.tab_layout_statistics) TabLayout tabLayoutStatistics;
    @BindView(R.id.tab_layout_activities) TabLayout tabLayoutActivities;
    @BindColor(R.color.colorDarkVehicleTabSelected) int vehicleDarkTabColor;
    @BindColor(R.color.colorDarkActivitiesTabSelected) int activitiesDarkTabColor;
    @BindColor(R.color.colorDarkStatisticsTabSelected) int statisticsDarkTabColor;
    @BindColor(R.color.colorDarkProfileTabSelected) int profileDarkTabColor;

    //@BindView(R.id.spn_main_vehicles) Spinner spinner;

    private MainContract.Presenter mPresenter;
    private String mSelectedVehicleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VehicleRepositoryImpl v = VehicleRepositoryImpl.getInstance();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        bottomBar.setDefaultTabPosition(1);

        MainPresenter mainPresenter = new MainPresenter(VehicleRepositoryImpl.getInstance(), this);
        setPresenter(mainPresenter);

        //setUpSpinner();
        mainPresenter.start();
       // spinner.setSelection(0);
        //TODO: Activities and Statistics Fragments should findById the spinner and subscribe to select events
        setUpBottomBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume: ", "main activity");
        mPresenter.start();
    }

/*    @OnClick({R.id.fab_main, R.id.fab_main_insurance, R.id.fab_main_refueling, R.id.fab_main_service})
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
            intent.putExtra(VEHICLE_ID, mSelectedVehicleId);
            startActivity(intent);
        }
    }*/

    @OnClick({R.id.fab_main, R.id.fab_main_insurance, R.id.fab_main_refueling, R.id.fab_main_service})
    public void onFabClick(FloatingActionButton button) {
        switch (button.getId()) {
            case R.id.fab_main:
                mPresenter.openAddEditVehicle();
                break;
            case R.id.fab_main_insurance:
                mPresenter.openAddEditInsurance();
                break;
            case R.id.fab_main_refueling:
                mPresenter.openAddEditRefueling();
                break;
            case R.id.fab_main_service:
                mPresenter.openAddEditService();
                break;
        }
    }

    private void setUpSpinner() {
/*        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedVehicleName = spinner.getItemAtPosition(position).toString();
                Log.d("onItemSelected: ", "name === " + selectedVehicleName);
                mPresenter.onSelectedVehicleChanged(selectedVehicleName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
    }

    private void setUpBottomBar() {
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
        ActivityUtils.hideFragments(getSupportFragmentManager(), StatisticsFragment.TAG, ProfileFragment.TAG, ActivitiesFragment.TAG);
        tabLayoutActivities.setVisibility(View.GONE);
        tabLayoutStatistics.setVisibility(View.GONE);

        ListVehicleFragment listVehicleFragment = (ListVehicleFragment) getSupportFragmentManager().findFragmentByTag(ListVehicleFragment.TAG);

        if (listVehicleFragment == null) {
            listVehicleFragment = ListVehicleFragment.newInstance();
            ActivityUtils.addFragmentToActivityWithTag(getSupportFragmentManager(), listVehicleFragment, R.id.frame_layout_main_content, ListVehicleFragment.TAG);
        }else {
            ActivityUtils.showFragment(getSupportFragmentManager(), listVehicleFragment);
        }
        setFabVisible();

        ListVehiclePresenter listVehiclePresenter = new ListVehiclePresenter(VehicleRepositoryImpl.getInstance(), listVehicleFragment);
        listVehicleFragment.setPresenter(listVehiclePresenter);

        //spinner.setVisibility(View.GONE);
    }

    private void onActivitiesTabSelected() {
        ActivityUtils.hideFragments(getSupportFragmentManager(), StatisticsFragment.TAG, ListVehicleFragment.TAG, ProfileFragment.TAG);

        setFabMenuVisible();
        tabLayoutStatistics.setVisibility(View.GONE);
        tabLayoutActivities.setVisibility(View.VISIBLE);

        ActivitiesFragment activitiesFragment = (ActivitiesFragment) getSupportFragmentManager().findFragmentByTag(ActivitiesFragment.TAG);

        if (activitiesFragment == null) {
            activitiesFragment = ActivitiesFragment.newInstance();
            ActivityUtils.addFragmentToActivityWithTag(getSupportFragmentManager(), activitiesFragment, R.id.frame_layout_main_content, ActivitiesFragment.TAG);
        }else {
            ActivityUtils.showFragment(getSupportFragmentManager(), activitiesFragment);
        }

        Log.d("onActivitiesTabSelected", "vehicleId = " + mSelectedVehicleId);

        //activitiesFragment.setVehicleId(mSelectedVehicleId);
       // spinner.setVisibility(View.VISIBLE);
    }

    private void onStatisticsTabSelected() {
        ActivityUtils.hideFragments(getSupportFragmentManager(), ListVehicleFragment.TAG, ProfileFragment.TAG, ActivitiesFragment.TAG);

        hideFabsAndTabActivities();
        tabLayoutStatistics.setVisibility(View.VISIBLE);

        StatisticsFragment statisticsFragment = (StatisticsFragment) getSupportFragmentManager().findFragmentByTag(StatisticsFragment.TAG);

        if (statisticsFragment == null) {
            statisticsFragment = new StatisticsFragment();
            ActivityUtils.addFragmentToActivityWithTag(getSupportFragmentManager(), statisticsFragment, R.id.frame_layout_main_content, StatisticsFragment.TAG);
        }else {
            ActivityUtils.showFragment(getSupportFragmentManager(), statisticsFragment);
        }

        //spinner.setVisibility(View.VISIBLE);
    }

    private void onProfileTabSelected() {
        hideFabsAndTabActivities();
        tabLayoutStatistics.setVisibility(View.GONE);

        ActivityUtils.hideFragments(getSupportFragmentManager(), StatisticsFragment.TAG, ListVehicleFragment.TAG, ActivitiesFragment.TAG);

        ProfileFragment profileFragment = (ProfileFragment) getSupportFragmentManager().findFragmentByTag(ProfileFragment.TAG);

        if (profileFragment == null) {
            profileFragment = ProfileFragment.newInstance();
            ActivityUtils.addFragmentToActivityWithTag(getSupportFragmentManager(), profileFragment, R.id.frame_layout_main_content, ProfileFragment.TAG);
        }else {
            ActivityUtils.showFragment(getSupportFragmentManager(), profileFragment);
        }
        //spinner.setVisibility(View.GONE);
    }

    private void setFabVisible() {
        fabMenu.hideMenu(true);
        fab.show(true);
    }

    private void setFabMenuVisible() {
        fab.hide(true);
        fabMenu.showMenu(true);
    }

    private void hideFabsAndTabActivities() {
        fabMenu.hideMenu(true);
        fab.hide(true);
        tabLayoutActivities.setVisibility(View.GONE);
    }

    private void setBarsColor(int statusBarColor, int actionBarColor) {
        final Window window = getWindow();
        final ActionBar actionBar = getSupportActionBar();

        window.setNavigationBarColor(actionBarColor);
        window.setStatusBarColor(statusBarColor);
        ColorDrawable colorDrawable = new ColorDrawable(actionBarColor);
        actionBar.setBackgroundDrawable(colorDrawable);
       // spinner.setBackgroundColor(actionBarColor);
        //spinner.setPopupBackgroundDrawable(colorDrawable);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.mPresenter = Preconditions.checkNotNull(presenter);
    }

/*    @Override
    public void setVehicleNames(List<String> items) {
        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this,
                R.layout.vehicles_spinner_item,
                items);
       // spinner.setAdapter(mAdapter);
    }*/

    @Override
    public void setSelectedVehicleId(String vehicleId) {
        Log.d("activity vehicleId", "id = " + vehicleId);
        this.mSelectedVehicleId = vehicleId;
    }



    @Override
    public void showVehicleNames(List<String> items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.vehicles_spinner_item, items);

    }

    @Override
    public void showAddEditVehicleUi() {
        startActivity(new Intent(this, AddEditVehicleActivity.class));
    }

    @Override
    public void showAddEditServiceUi(String vehicleId) {
        Intent intent = new Intent(this, AddEditServiceActivity.class);
        intent.putExtra(VEHICLE_ID, vehicleId);
        startActivity(intent);
    }

    @Override
    public void showAddEditInsuranceUi(String vehicleId) {
        Intent intent = new Intent(this, AddEditInsuranceActivity.class);
        intent.putExtra(VEHICLE_ID, vehicleId);
        startActivity(intent);
    }

    @Override
    public void showAddEditRefuelingUi(String vehicleId) {
        Intent intent = new Intent(this, AddEditRefuelingActivity.class);
        intent.putExtra(VEHICLE_ID, vehicleId);
        startActivity(intent);
    }

    @Override
    public void showVehiclesUi() {

    }

    @Override
    public void showActivitiesUi() {

    }

    @Override
    public void showStatisticsUi() {

    }

    @Override
    public void showProfileUi() {

    }
}
