package elsys.mycar.mycarpro.list.activities;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.data.Constants;
import elsys.mycar.mycarpro.data.model.Insurance;
import elsys.mycar.mycarpro.data.model.Refueling;
import elsys.mycar.mycarpro.data.model.Service;
import elsys.mycar.mycarpro.data.repository.activities.insurance.InsuranceRepositoryImpl;
import elsys.mycar.mycarpro.data.repository.activities.refueling.RefuelingRepositoryImpl;
import elsys.mycar.mycarpro.data.repository.activities.service.ServiceRepositoryImpl;
import elsys.mycar.mycarpro.homescreen.VehiclesSpinnerAdapter;
import elsys.mycar.mycarpro.list.activities.insurances.ListInsurancesFragment;
import elsys.mycar.mycarpro.list.activities.insurances.ListInsurancesPresenter;
import elsys.mycar.mycarpro.list.activities.refuelings.ListRefuelingPresenter;
import elsys.mycar.mycarpro.list.activities.refuelings.ListRefuelingsFragment;
import elsys.mycar.mycarpro.list.activities.services.ListServicesFragment;
import elsys.mycar.mycarpro.list.activities.services.ListServicesPresenter;
import elsys.mycar.mycarpro.list.activities.viewpager.ActivitiesViewPagerAdapter;
import elsys.mycar.mycarpro.list.base.BaseActivitiesPresenter;

public class ActivitiesFragment extends Fragment {

    public static final String TAG = "ActivitiesFragment";

    @BindView(R.id.view_pager_activities) protected ViewPager viewPager;

    private TabLayout mTabLayout;
    private Unbinder mUnbinder;
    private String mVehicleId;
    private List<BaseActivitiesPresenter> mNestedPresenters;

    public static ActivitiesFragment newInstance() {
        return new ActivitiesFragment();
    }

    public void setVehicleId(String vehicleId) {
        mVehicleId = vehicleId;
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activities, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Spinner spinner = (Spinner) getActivity().findViewById(R.id.spn_main_vehicles);
        final SpinnerAdapter spinnerAdapter = spinner.getAdapter();

        if (spinnerAdapter instanceof VehiclesSpinnerAdapter) {

            mNestedPresenters = new ArrayList<>(3);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                    String vehicleId = ((VehiclesSpinnerAdapter) spinnerAdapter).getVehicleIdAtPosition(position);
                    for (BaseActivitiesPresenter presenter : mNestedPresenters) {
                        presenter.onVehicleChange(vehicleId);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            setUpViewPager();

            mTabLayout = (TabLayout) getActivity().findViewById(R.id.tab_layout_activities);
            setUpTabLayout();
        } else {
            throw new RuntimeException("spinner adapter must be instance of VehiclesSpinnerAdapter");
        }
    }

    private void setUpViewPager() {
        ActivitiesViewPagerAdapter adapter = new ActivitiesViewPagerAdapter(getChildFragmentManager());

        ListServicesFragment listServicesFragment = new ListServicesFragment();
        ListServicesPresenter servicePresenter = new ListServicesPresenter(mVehicleId, listServicesFragment, new ServiceRepositoryImpl(), true);
        listServicesFragment.setPresenter(servicePresenter);
        mNestedPresenters.add(servicePresenter);
        adapter.addFragment(listServicesFragment);

        ListInsurancesFragment listInsurancesFragment = new ListInsurancesFragment();
        ListInsurancesPresenter insurancePresenter = new ListInsurancesPresenter(mVehicleId, listInsurancesFragment, new InsuranceRepositoryImpl(), true);
        listInsurancesFragment.setPresenter(insurancePresenter);
        mNestedPresenters.add(insurancePresenter);
        adapter.addFragment(listInsurancesFragment);

        ListRefuelingsFragment listRefuelingsFragment = new ListRefuelingsFragment();
        ListRefuelingPresenter refuelingPresenter = new ListRefuelingPresenter(mVehicleId, listRefuelingsFragment, new RefuelingRepositoryImpl(), true);
        listRefuelingsFragment.setPresenter(refuelingPresenter);
        mNestedPresenters.add(refuelingPresenter);
        adapter.addFragment(listRefuelingsFragment);

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
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
                BaseActivitiesPresenter currentPresenter = mNestedPresenters.get(tab.getPosition());
                if (currentPresenter.isDataMissing()) {
                    currentPresenter.start();
                }
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