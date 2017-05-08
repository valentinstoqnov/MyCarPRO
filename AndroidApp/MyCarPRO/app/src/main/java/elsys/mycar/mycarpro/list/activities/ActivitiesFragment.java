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
import android.widget.SpinnerAdapter;

import com.google.common.base.Preconditions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.homescreen.VehiclesSpinnerAdapter;
import elsys.mycar.mycarpro.list.activities.insurances.ListInsurancesFragment;
import elsys.mycar.mycarpro.list.activities.insurances.ListInsurancesPresenter;
import elsys.mycar.mycarpro.list.activities.refuelings.ListRefuelingPresenter;
import elsys.mycar.mycarpro.list.activities.refuelings.ListRefuelingsFragment;
import elsys.mycar.mycarpro.list.activities.services.ListServicesFragment;
import elsys.mycar.mycarpro.list.activities.services.ListServicesPresenter;
import elsys.mycar.mycarpro.list.activities.viewpager.ActivitiesViewPagerAdapter;
import elsys.mycar.mycarpro.util.ProviderUtils;
import elsys.mycar.mycarpro.util.AuthenticationUtils;

public class ActivitiesFragment extends Fragment implements ActivitiesContract.View{

    public static final String TAG = "ActivitiesFragment";

    @BindView(R.id.view_pager_activities) ViewPager viewPager;

    private TabLayout tabLayout;
    private Unbinder mUnbinder;

    private ActivitiesContract.Presenter mPresenter;
    private Spinner mSpinner;
    private ListServicesPresenter mListServicesPresenter;
    private ListInsurancesPresenter mListInsurancesPresenter;
    private ListRefuelingPresenter mListRefuelingPresenter;
    private AuthenticationUtils mAuthenticationUtils;

    public static ActivitiesFragment newInstance() {
        return new ActivitiesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAuthenticationUtils = new AuthenticationUtils(getActivity());
        View view = inflater.inflate(R.layout.fragment_activities, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAuthenticationUtils.checkUser()) {
            mPresenter.onVehicleChange(getSelectedVehicleName(mSpinner.getSelectedItemPosition()));
            mPresenter.start();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mSpinner = (Spinner) getActivity().findViewById(R.id.spn_main_vehicles);

        SpinnerAdapter spinnerAdapter = mSpinner.getAdapter();
        if (spinnerAdapter instanceof VehiclesSpinnerAdapter) {
            mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //mPresenter.onVehicleChange(getSelectedVehicleName(position));

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            setUpViewPager();

            tabLayout = (TabLayout) getActivity().findViewById(R.id.tab_layout_activities);
            setUpTabLayout();
        }else {
            throw new RuntimeException("spinner adapter must be instance of VehiclesSpinnerAdapter");
        }
    }

    private String getSelectedVehicleName(int position) {
        Object itemAtPosition = mSpinner.getItemAtPosition(position);

        String vehicleName = null;

        if (itemAtPosition != null) {
            vehicleName = itemAtPosition.toString();
        }

        return vehicleName;
    }

    private void refreshFragmentAtPosition(int position) {
        Log.d(TAG, "refreshFragmentAtPosition: " + position);
        switch (position) {
            case 0:
                mListServicesPresenter.start();
                break;
            case 1:
                mListInsurancesPresenter.start();
                break;
            case 2:
                mListRefuelingPresenter.start();
                break;
        }
    }

    private void setUpViewPager() {
        ActivitiesViewPagerAdapter adapter = new ActivitiesViewPagerAdapter(getChildFragmentManager());

        elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepositoryImpl vehicleRepository = ProviderUtils.getVehicleRepository(mAuthenticationUtils.getToken());

        String vehicleName = getSelectedVehicleName(mSpinner.getSelectedItemPosition());

       // ListServicesFragment listServicesFragment = null;// = ListServicesFragment.newInstance();
        mListServicesPresenter = new ListServicesPresenter(/*vehicleName, vehicleRepository, listServicesFragment, true*/);
        //listServicesFragment.setPresenter(mListServicesPresenter);
        //adapter.addFragment(listServicesFragment);

        //ListInsurancesFragment listInsurancesFragment;// = ListInsurancesFragment.newInstance();
        mListInsurancesPresenter = new ListInsurancesPresenter(vehicleName, vehicleRepository, null, true);
       // listInsurancesFragment.setPresenter(mListInsurancesPresenter);
       // adapter.addFragment(listInsurancesFragment);

        //ListRefuelingsFragment listRefuelingsFragment;// = ListRefuelingsFragment.newInstance();
        mListRefuelingPresenter = new ListRefuelingPresenter(vehicleName, vehicleRepository, null, true);
        //listRefuelingsFragment.setPresenter(mListRefuelingPresenter);
        //adapter.addFragment(listRefuelingsFragment);

        viewPager.setAdapter(adapter);

        mPresenter.onVehicleChange(vehicleName);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void setPresenter(ActivitiesContract.Presenter presenter) {
        this.mPresenter = Preconditions.checkNotNull(presenter);
    }


    private void setUpTabLayout() {
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_service).getIcon();
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_insurance).getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_gas_station).getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                refreshFragmentAtPosition(tab.getPosition());
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

    @Override
    public void showVehicleContent(String vehicleId) {
      //  mListServicesPresenter.onVehicleChanged(vehicleId);
        mListInsurancesPresenter.onVehicleChanged(vehicleId);
        //mListRefuelingPresenter.onVehicleChanged(vehicleId);
        refreshFragmentAtPosition(viewPager.getCurrentItem());
    }
}
