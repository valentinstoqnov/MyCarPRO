package elsys.mycar.mycarpro.list.activities;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.google.common.collect.HashBiMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.data.VehicleRepositoryImpl;
import elsys.mycar.mycarpro.homescreen.MainActivity;
import elsys.mycar.mycarpro.list.activities.insurances.ListInsurancesFragment;
import elsys.mycar.mycarpro.list.activities.insurances.ListInsurancesPresenter;
import elsys.mycar.mycarpro.list.activities.refuelings.ListRefuelingPresenter;
import elsys.mycar.mycarpro.list.activities.refuelings.ListRefuelingsFragment;
import elsys.mycar.mycarpro.list.activities.services.ListServicesFragment;
import elsys.mycar.mycarpro.list.activities.services.ListServicesPresenter;

public class ActivitiesFragment extends Fragment {

    public static final String TAG = "ActivitiesFragment";

    @BindView(R.id.view_pager_activities) ViewPager viewPager;

    private Spinner spinner;
    private TabLayout tabLayout;
    private Unbinder mUnbinder;
    private ListServicesPresenter mListServicesPresenter;
    private ListInsurancesPresenter mListInsurancesPresenter;
    private ListRefuelingPresenter mListRefuelingPresenter;
    private String mVehicleId;

    public static ActivitiesFragment newInstance() {
        return new ActivitiesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activities, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tabLayout = (TabLayout) getActivity().findViewById(R.id.tab_layout_activities);
        setUpViewPager();
        setUpTabLayout();
        spinner = (Spinner) getActivity().findViewById(R.id.spn_main_vehicles);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = HashBiMap.create(VehicleRepositoryImpl.getInstance().getVehicleIdToNameHash()).inverse().get(spinner.getSelectedItem().toString());
                switch (viewPager.getCurrentItem()) {
                    case 0:
                        mListServicesPresenter.onVehicleChanged(mVehicleId);
                        break;
                    case 1:
                        mListInsurancesPresenter.onVehicleChanged(mVehicleId);
                        break;
                    case 2:
                        mListRefuelingPresenter.onVehicleChanged(mVehicleId);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private void setUpViewPager() {
        Log.d("SET UP VP", "CALLED . . . . . .");
        ActivitiesViewPagerAdapter mAdapter = new ActivitiesViewPagerAdapter(getChildFragmentManager());

       // String vehicleId = getArguments().getString(MainActivity.VEHICLE_ID);
        //Log.d("FRAGMENT", "id = " + vehicleId);
        VehicleRepositoryImpl vehicleRepository = VehicleRepositoryImpl.getInstance();

        ListServicesFragment listServicesFragment = ListServicesFragment.newInstance();
        mListServicesPresenter = new ListServicesPresenter(mVehicleId, vehicleRepository, listServicesFragment, true);

        listServicesFragment.setPresenter(mListServicesPresenter);

        ListInsurancesFragment listInsurancesFragment = ListInsurancesFragment.newInstance();
        mListInsurancesPresenter = new ListInsurancesPresenter(mVehicleId, vehicleRepository, listInsurancesFragment, true);

        listInsurancesFragment.setPresenter(mListInsurancesPresenter);

        ListRefuelingsFragment listRefuelingsFragment = ListRefuelingsFragment.newInstance();
        mListRefuelingPresenter = new ListRefuelingPresenter(mVehicleId, vehicleRepository, listRefuelingsFragment, true);

        listRefuelingsFragment.setPresenter(mListRefuelingPresenter);

        mAdapter.addFragment(listServicesFragment);
        mAdapter.addFragment(listInsurancesFragment);
        mAdapter.addFragment(listRefuelingsFragment);

        ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected: trigger");
                switch (position) {
                    case 0:
                        mListServicesPresenter.onVehicleChanged(mVehicleId);
                        break;
                    case 1:
                        mListInsurancesPresenter.onVehicleChanged(mVehicleId);
                        break;
                    case 2:
                        mListRefuelingPresenter.onVehicleChanged(mVehicleId);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };

        viewPager.addOnPageChangeListener(listener);

        viewPager.setCurrentItem(0);
        viewPager.setAdapter(mAdapter);
    }

    private void setUpTabLayout() {
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_service).getIcon();
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_insurance).getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_gas_station).getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void setVehicleId(String vehicleId) {
        this.mVehicleId = vehicleId;
    }
}
