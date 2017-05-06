package elsys.mycar.mycarpro.homescreen;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.common.collect.HashBiMap;
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
import elsys.mycar.mycarpro.base.BaseView;
import elsys.mycar.mycarpro.data.VehicleRepository;
import elsys.mycar.mycarpro.data.VehicleRepositoryImpl;
import elsys.mycar.mycarpro.list.activities.ActivitiesFragment;
import elsys.mycar.mycarpro.list.activities.ActivitiesPresenter;
import elsys.mycar.mycarpro.list.vehicles.ListVehicleFragment;
import elsys.mycar.mycarpro.list.vehicles.ListVehiclePresenter;
import elsys.mycar.mycarpro.loginscreen.LoginActivity;
import elsys.mycar.mycarpro.model.Service;
import elsys.mycar.mycarpro.profile.ProfileFragment;
import elsys.mycar.mycarpro.statistics.StatisticsFragment;
import elsys.mycar.mycarpro.util.ActivityUtils;
import elsys.mycar.mycarpro.util.FragmentManagingUtils;
import elsys.mycar.mycarpro.util.ProviderUtils;
import elsys.mycar.mycarpro.util.StringUtils;
import elsys.mycar.mycarpro.util.TokenUtils;

public class HomeActivity extends AppCompatActivity implements MainContract.View {

    public static final String VEHICLE_ID = "VEHICLE_ID";

    @BindView(R.id.bottom_bar_main) BottomBar bottomBar;
    @BindView(R.id.fab_main) FloatingActionButton fab;
    @BindView(R.id.fab_menu_main) FloatingActionMenu fabMenu;
    @BindView(R.id.tab_layout_statistics) TabLayout tabLayoutStatistics;
    @BindView(R.id.tab_layout_activities) TabLayout tabLayoutActivities;
    @BindView(R.id.spn_main_vehicles) Spinner spinner;

    @BindColor(R.color.colorDarkVehicleTabSelected) int vehicleDarkTabColor;
    @BindColor(R.color.colorDarkActivitiesTabSelected) int activitiesDarkTabColor;
    @BindColor(R.color.colorDarkStatisticsTabSelected) int statisticsDarkTabColor;
    @BindColor(R.color.colorDarkProfileTabSelected) int profileDarkTabColor;

    private FragmentManagingUtils fragmentManagingUtils;
    private MainContract.Presenter mPresenter;
    private TokenUtils mTokenUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTokenUtils = new TokenUtils(this);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        fragmentManagingUtils = new FragmentManagingUtils(getSupportFragmentManager(), R.id.frame_layout_main_content);

        MainPresenter mainPresenter = new MainPresenter(ProviderUtils.getVehicleRepository(mTokenUtils.getToken()), this);
        setPresenter(mainPresenter);

        fabMenu.setClosedOnTouchOutside(true);

        setUpBottomBar();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //mPresenter.onSelectedVehicleChanged(spinner.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mTokenUtils.checkToken()) {
            mPresenter.start();
        }
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setSelectedVehicleId(String vehicleId) {

    }

    @Override
    public void showVehicleNames(List<String> items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.vehicles_spinner_item, items);
        spinner.setAdapter(adapter);
    }

    @Override
    public void showAddEditVehicleUi() {
        Intent intent = new Intent(this, AddEditVehicleActivity.class);
        startActivity(intent);
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
        Fragment fragment = fragmentManagingUtils.addOrShowFragment(ListVehicleFragment.TAG);
        ListVehicleFragment listVehicleFragment = (ListVehicleFragment) fragment;

        ListVehiclePresenter listVehiclePresenter = new ListVehiclePresenter(
                ProviderUtils.getVehicleRepository(mTokenUtils.getToken()), listVehicleFragment
        );

        listVehicleFragment.setPresenter(listVehiclePresenter);
    }

    @Override
    public void showActivitiesUi() {
        Fragment fragment = fragmentManagingUtils.addOrShowFragment(ActivitiesFragment.TAG);
        ActivitiesFragment activitiesFragment = (ActivitiesFragment) fragment;
        ActivitiesPresenter activitiesPresenter = new ActivitiesPresenter(VehicleRepositoryImpl.getInstance(), activitiesFragment);
        activitiesFragment.setPresenter(activitiesPresenter);
    }

    @Override
    public void showStatisticsUi() {
        fragmentManagingUtils.addOrShowFragment(StatisticsFragment.TAG);
    }

    @Override
    public void showProfileUi() {
        fragmentManagingUtils.addOrShowFragment(ProfileFragment.TAG);
    }

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

    private void setUpBottomBar() {
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                    int primaryColor = bottomBar.getTabWithId(tabId).getBarColorWhenSelected();
                    int primaryDarkColor = activitiesDarkTabColor;

                    switch (tabId) {
                        case R.id.tab_vehicles:
                            mPresenter.openVehicles();
                            primaryDarkColor = vehicleDarkTabColor;
                            break;
                        case R.id.tab_activities:
                            mPresenter.openActivities();
                            break;
                        case R.id.tab_statistics:
                            mPresenter.openStatistics();
                            primaryDarkColor = statisticsDarkTabColor;
                            break;
                        case R.id.tab_user_profile:
                            mPresenter.openProfile();
                            primaryDarkColor = profileDarkTabColor;
                            break;
                    }

                    setUiColor(primaryDarkColor, primaryColor);
                    switchUiComponents(tabId);
                }
       //     }
        });
    }

    private void setUiColor(int primaryDarkColor, int primaryColor) {
        final Window window = getWindow();
        final ActionBar actionBar = getSupportActionBar();

        window.setNavigationBarColor(primaryColor);
        window.setStatusBarColor(primaryDarkColor);
        ColorDrawable colorDrawable = new ColorDrawable(primaryColor);
        actionBar.setBackgroundDrawable(colorDrawable);
    }

    private void switchUiComponents(int tabId) {
        switchFabs(tabId);
        switchTabs(tabId);
        switchSpinnerVisibility(tabId);
    }

    private void switchFabs(int tabId) {
        switch (tabId) {
            case R.id.tab_activities:
                showFabMenu();
                break;
            case R.id.tab_vehicles:
                showFab();
                break;
            default:
                hideFabs();
                break;
        }
    }

    private void switchTabs(int tabId) {
        switch (tabId) {
            case R.id.tab_activities:
                showActivitiesTabLayout();
                break;
            case R.id.tab_statistics:
                showStatisticsTabLayout();
                break;
            default:
                hideTabs();
                break;
        }
    }

    private void switchSpinnerVisibility(int tabId) {
        if (tabId == R.id.tab_activities || tabId == R.id.tab_statistics) {
            if (!spinner.isShown()) {
                spinner.setVisibility(View.VISIBLE);
            }
        }else {
            if (spinner.isShown()) {
                spinner.setVisibility(View.GONE);
            }
        }
    }

    private void showFab() {
        if (fabMenu.isShown()) {
            fabMenu.hideMenu(true);
        }
        if (fab.isHidden()) {
            fab.show(true);
        }
    }

    private void showFabMenu() {
        if (fab.isShown()) {
            fab.hide(true);
        }
        if (fabMenu.isMenuHidden()) {
            fabMenu.showMenu(true);
        }
    }

    private void hideFabs() {
        if (fabMenu.isShown()) {
            fabMenu.hideMenu(true);
        }

        if (fab.isShown()) {
            fab.hide(true);
        }
    }

    private void showActivitiesTabLayout() {
        if (!tabLayoutActivities.isShown()) {
            tabLayoutStatistics.setVisibility(View.GONE);
            tabLayoutActivities.setVisibility(View.VISIBLE);
        }
    }

    private void showStatisticsTabLayout() {
        if (!tabLayoutStatistics.isShown()) {
            tabLayoutActivities.setVisibility(View.GONE);
            tabLayoutStatistics.setVisibility(View.VISIBLE);
        }
    }

    private void hideTabs() {
        if (tabLayoutActivities.isShown()) {
            tabLayoutActivities.setVisibility(View.GONE);
        }

        if (tabLayoutStatistics.isShown()) {
            tabLayoutStatistics.setVisibility(View.GONE);
        }
    }
}
