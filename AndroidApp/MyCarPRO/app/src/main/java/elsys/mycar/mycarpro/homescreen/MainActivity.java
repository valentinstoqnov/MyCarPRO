package elsys.mycar.mycarpro.homescreen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.common.base.Preconditions;
import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.addedit.insurance.AddEditInsuranceActivity;
import elsys.mycar.mycarpro.addedit.refueling.AddEditRefuelingActivity;
import elsys.mycar.mycarpro.addedit.service.AddEditServiceActivity;
import elsys.mycar.mycarpro.addedit.vehicle.AddEditVehicleActivity;
import elsys.mycar.mycarpro.base.FirebaseAuthBaseActivity;
import elsys.mycar.mycarpro.data.Constants;
import elsys.mycar.mycarpro.data.repository.user.UserRepositoryImpl;
import elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepositoryImpl;
import elsys.mycar.mycarpro.list.activities.ActivitiesFragment;
import elsys.mycar.mycarpro.list.activities.ActivitiesPresenter;
import elsys.mycar.mycarpro.list.vehicles.ListVehicleFragment;
import elsys.mycar.mycarpro.list.vehicles.ListVehiclePresenter;
import elsys.mycar.mycarpro.profile.ProfileFragment;
import elsys.mycar.mycarpro.profile.ProfilePresenter;
import elsys.mycar.mycarpro.statistics.StatisticsFragment;
import elsys.mycar.mycarpro.util.FragmentManagingUtils;

public class MainActivity extends FirebaseAuthBaseActivity implements MainContract.View {

    public static final int REQUEST_CODE_NEW_VEHICLE = 1232;

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
    private VehiclesSpinnerAdapter mSpinnerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        mSpinnerAdapter = new VehiclesSpinnerAdapter(this, R.layout.vehicles_spinner_item, new ArrayList<>(0));
        spinner.setAdapter(mSpinnerAdapter);
        MainPresenter mainPresenter = new MainPresenter(getCurrentUserId(), new VehicleRepositoryImpl(), this);
        setPresenter(mainPresenter);
        fragmentManagingUtils = new FragmentManagingUtils(getSupportFragmentManager(), R.id.frame_layout_main_content);

        fabMenu.setClosedOnTouchOutside(true);
        setUpBottomBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showVehicleItemsInDropdown(HashMap<String, String> items) {
        mSpinnerAdapter.replaceData(items);
        spinner.setAdapter(mSpinnerAdapter);
    }

    @Override
    public void showAddEditVehicleUi() {
        Intent intent = new Intent(this, AddEditVehicleActivity.class);
        intent.putExtra(Constants.USER_ID, getCurrentUserId());
        startActivityForResult(intent, REQUEST_CODE_NEW_VEHICLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_NEW_VEHICLE) {
            mPresenter.setDataMissing();
        }
    }

    @Override
    public void showAddEditServiceUi(String vehicleId) {
        Intent intent = new Intent(this, AddEditServiceActivity.class);
        intent.putExtra(Constants.VEHICLE_ID, vehicleId);
        startActivity(intent);
    }

    @Override
    public void showAddEditInsuranceUi(String vehicleId) {
        Intent intent = new Intent(this, AddEditInsuranceActivity.class);
        intent.putExtra(Constants.VEHICLE_ID, vehicleId);
        startActivity(intent);
    }

    @Override
    public void showAddEditRefuelingUi(String vehicleId) {
        Intent intent = new Intent(this, AddEditRefuelingActivity.class);
        intent.putExtra(Constants.VEHICLE_ID, vehicleId);
        startActivity(intent);
    }

    @Override
    public void showVehiclesUi() {
        Fragment fragment = fragmentManagingUtils.addOrShowFragment(ListVehicleFragment.TAG);
        ListVehicleFragment listVehicleFragment = (ListVehicleFragment) fragment;
        ListVehiclePresenter listVehiclePresenter = new ListVehiclePresenter(getCurrentUserId(), new VehicleRepositoryImpl(), listVehicleFragment, true);
        listVehicleFragment.setPresenter(listVehiclePresenter);
    }

    @Override
    public void showActivitiesUi() {
        Fragment fragment = fragmentManagingUtils.addOrShowFragment(ActivitiesFragment.TAG);
        ActivitiesFragment activitiesFragment = (ActivitiesFragment) fragment;
        ActivitiesPresenter activitiesPresenter = new ActivitiesPresenter(getSelectedVehicleId(), activitiesFragment, new VehicleRepositoryImpl(), true);
        activitiesFragment.setPresenter(activitiesPresenter);
       /* Fragment fragment = fragmentManagingUtils.addOrShowFragment(ActivitiesFragment.TAG);
        ActivitiesFragment activitiesFragment = (ActivitiesFragment) fragment;
        ActivitiesPresenter activitiesPresenter = new ActivitiesPresenter(ProviderUtils.getVehicleRepository(mAuthenticationUtils.getToken()), activitiesFragment);
        activitiesFragment.setPresenter(activitiesPresenter);*/
    }

    @Override
    public void showStatisticsUi() {
        fragmentManagingUtils.addOrShowFragment(StatisticsFragment.TAG);
    }

    @Override
    public void showProfileUi() {
        Fragment fragment = fragmentManagingUtils.addOrShowFragment(ProfileFragment.TAG);
        ProfileFragment profileFragment = (ProfileFragment) fragment;
        ProfilePresenter profilePresenter = new ProfilePresenter(getCurrentUserId(), profileFragment, new UserRepositoryImpl(), true);
        profileFragment.setPresenter(profilePresenter);
    }

    @Override
    public String getCurrentUserId() {
        return super.getCurrentUserId();
    }

    @OnClick({R.id.fab_main, R.id.fab_main_insurance, R.id.fab_main_refueling, R.id.fab_main_service})
    public void onFabClick(FloatingActionButton button) {
        switch (button.getId()) {
            case R.id.fab_main:
                mPresenter.openAddEditVehicle();
                break;
            case R.id.fab_main_insurance:
                mPresenter.openAddEditInsurance(getSelectedVehicleId());
                break;
            case R.id.fab_main_refueling:
                mPresenter.openAddEditRefueling(getSelectedVehicleId());
                break;
            case R.id.fab_main_service:
                mPresenter.openAddEditService(getSelectedVehicleId());
                break;
        }
    }

    private String getSelectedVehicleId() {
        int position = spinner.getSelectedItemPosition();
        return position >= 0 ? mSpinnerAdapter.getVehicleIdAtPosition(position) : null;
    }

    private void setUpBottomBar() {
        bottomBar.setOnTabSelectListener(tabId -> {
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
                spinner.setVisibility(android.view.View.VISIBLE);
            }
        }else {
            if (spinner.isShown()) {
                spinner.setVisibility(android.view.View.GONE);
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
            tabLayoutStatistics.setVisibility(android.view.View.GONE);
            tabLayoutActivities.setVisibility(android.view.View.VISIBLE);
        }
    }

    private void showStatisticsTabLayout() {
        if (!tabLayoutStatistics.isShown()) {
            tabLayoutActivities.setVisibility(android.view.View.GONE);
            tabLayoutStatistics.setVisibility(android.view.View.VISIBLE);
        }
    }

    private void hideTabs() {
        if (tabLayoutActivities.isShown()) {
            tabLayoutActivities.setVisibility(android.view.View.GONE);
        }

        if (tabLayoutStatistics.isShown()) {
            tabLayoutStatistics.setVisibility(android.view.View.GONE);
        }
    }
}
