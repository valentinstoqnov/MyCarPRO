package elsys.mycar.mycarpro.list.activities.viewpager;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepositoryImpl;
import elsys.mycar.mycarpro.list.activities.ActivitiesContract;
import elsys.mycar.mycarpro.list.activities.insurances.ListInsurancesFragment;
import elsys.mycar.mycarpro.list.activities.insurances.ListInsurancesPresenter;
import elsys.mycar.mycarpro.list.activities.refuelings.ListRefuelingPresenter;
import elsys.mycar.mycarpro.list.activities.refuelings.ListRefuelingsFragment;
import elsys.mycar.mycarpro.list.activities.services.ListServicesFragment;
import elsys.mycar.mycarpro.list.activities.services.ListServicesPresenter;
import elsys.mycar.mycarpro.util.AuthenticationUtils;
import elsys.mycar.mycarpro.util.ProviderUtils;

public class ActFr extends Fragment implements ActivitiesContract.View1 {

    public static final String TAG = "ActivitiesFragment";

    @BindView(R.id.view_pager_activities) ViewPager viewPager;

    private TabLayout mTabLayout;
    private Unbinder mUnbinder;

    private ActivitiesContract.Presenter1 mPresenter;
    private Spinner mSpinner;
    private AuthenticationUtils mAuthenticationUtils;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAuthenticationUtils = new AuthenticationUtils(getActivity());
        View view = inflater.inflate(R.layout.fragment_activities, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void setPresenter(ActivitiesContract.Presenter1 presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAuthenticationUtils.checkUser()) {
            mPresenter.start();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSpinner = (Spinner) getActivity().findViewById(R.id.spn_main_vehicles);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        setUpViewPager();

        mTabLayout = (TabLayout) getActivity().findViewById(R.id.tab_layout_activities);
        setUpTabLayout();
    }

    private void setUpViewPager() {
        ActivitiesViewPagerAdapter adapter = new ActivitiesViewPagerAdapter(getChildFragmentManager());

        VehicleRepositoryImpl vehicleRepository = ProviderUtils.getVehicleRepository(mAuthenticationUtils.getToken());

        ListServicesFragment listServicesFragment = ListServicesFragment.newInstance();
        String vehicleName = "asda";
        ListServicesPresenter listServicesPresenter = new ListServicesPresenter();
        //listServicesFragment.setPresenter(listServicesPresenter);
        adapter.addFragment(listServicesFragment);

        ListInsurancesFragment listInsurancesFragment = ListInsurancesFragment.newInstance();
        ListInsurancesPresenter listInsurancesPresenter = new ListInsurancesPresenter(vehicleName, vehicleRepository, listInsurancesFragment, true);
        //listInsurancesFragment.setPresenter(listInsurancesPresenter);
        adapter.addFragment(listInsurancesFragment);

        ListRefuelingsFragment listRefuelingsFragment = ListRefuelingsFragment.newInstance();
        ListRefuelingPresenter listRefuelingPresenter = new ListRefuelingPresenter(vehicleName, vehicleRepository, listRefuelingsFragment, true);
       // listRefuelingsFragment.setPresenter(listRefuelingPresenter);
        adapter.addFragment(listRefuelingsFragment);

        viewPager.setAdapter(adapter);

    }

    private void setUpTabLayout() {
        mTabLayout.setupWithViewPager(viewPager);

        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_service).getIcon();
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_insurance).getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_gas_station).getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
