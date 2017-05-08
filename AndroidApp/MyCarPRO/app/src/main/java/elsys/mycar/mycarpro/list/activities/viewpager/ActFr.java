package elsys.mycar.mycarpro.list.activities.viewpager;

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
import elsys.mycar.mycarpro.data.model.Insurance;
import elsys.mycar.mycarpro.data.model.Refueling;
import elsys.mycar.mycarpro.data.model.Service;
import elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepositoryImpl;
import elsys.mycar.mycarpro.homescreen.VehiclesSpinnerAdapter;
import elsys.mycar.mycarpro.list.activities.ActivitiesContract;
import elsys.mycar.mycarpro.list.activities.insurances.ListInsurancesFragment;
import elsys.mycar.mycarpro.list.activities.refuelings.ListRefuelingsFragment;
import elsys.mycar.mycarpro.list.activities.services.ListServicesFragment;
import elsys.mycar.mycarpro.list.idk.IDKPresenter;
import elsys.mycar.mycarpro.util.AuthenticationUtils;
import elsys.mycar.mycarpro.util.ProviderUtils;

public class ActFr extends Fragment implements ActivitiesContract.View1 {

    public static final String TAG = "ActivitiesFragment";

    @BindView(R.id.view_pager_activities) ViewPager viewPager;

    private TabLayout mTabLayout;
    private Unbinder mUnbinder;

    private ActivitiesContract.Presenter1 mPresenter;
    private AuthenticationUtils mAuthenticationUtils;
    private List<IDKPresenter> mNestedPresenters;

    public static ActFr newInstance() {
        return new ActFr();
    }

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
        Spinner spinner = (Spinner) getActivity().findViewById(R.id.spn_main_vehicles);
        final SpinnerAdapter spinnerAdapter = spinner.getAdapter();

        if (spinnerAdapter instanceof VehiclesSpinnerAdapter) {

            mNestedPresenters = new ArrayList<>(3);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String vehicleId = ((VehiclesSpinnerAdapter) spinnerAdapter).getVehicleIdAtPosition(position);
                    mPresenter.onVehicleChanged(vehicleId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            setUpViewPager();

            mTabLayout = (TabLayout) getActivity().findViewById(R.id.tab_layout_activities);
            setUpTabLayout();
        }else {
            throw new RuntimeException("spinner adapter must be instance of VehiclesSpinnerAdapter");
        }
    }

    private void setUpViewPager() {
        ActivitiesViewPagerAdapter adapter = new ActivitiesViewPagerAdapter(getChildFragmentManager());

        ListServicesFragment listServicesFragment = new ListServicesFragment();
        IDKPresenter<Service> servicePresenter = new IDKPresenter<>(listServicesFragment, true);
        listServicesFragment.setPresenter(servicePresenter);
        mNestedPresenters.add(servicePresenter);
        adapter.addFragment(listServicesFragment);

        ListInsurancesFragment listInsurancesFragment = new ListInsurancesFragment();
        IDKPresenter<Insurance> insurancePresenter = new IDKPresenter<>(listInsurancesFragment, true);
        listInsurancesFragment.setPresenter(insurancePresenter);
        mNestedPresenters.add(insurancePresenter);
        adapter.addFragment(listInsurancesFragment);

        ListRefuelingsFragment listRefuelingsFragment = new ListRefuelingsFragment();
        IDKPresenter<Refueling> refuelingPresenter = new IDKPresenter<>(listRefuelingsFragment, true);
        listRefuelingsFragment.setPresenter(refuelingPresenter);
        mNestedPresenters.add(refuelingPresenter);
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
                int tabPosition = tab.getPosition();

                if (mNestedPresenters.get(tabPosition).isDataMissing()) {
                    switch (tabPosition) {
                        case 0:
                            mPresenter.provideServices();
                            break;
                        case 1:
                            mPresenter.provideInsurances();
                            break;
                        case 2:
                            mPresenter.provideRefuelings();
                            break;
                    }
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

    @Override
    public void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showServices(List<Service> services) {
        List<Service> s = new ArrayList<>();
        s.add(new Service("asd", "asdadsdsa", "asdsd", 213, 2132, "asdsda"));
        s.add(new Service("asasdd", "asdaasdasddsdsa", "asdsd", 213, 2132, "asdsda"));
        s.add(new Service("asgsfd", "asdaawqe2qdsdsa", "asdsd", 213, 2132, "asdsda"));
        s.add(new Service("asdasda", "asdads2dsa", "asdsd", 213, 2132, "asdsda"));
        s.add(new Service("asasdd", "asdaasd12asddsdsa", "asdsd", 213, 2132, "asdsda"));
        s.add(new Service("asggersfd", "asdaawq123eqdsdsa", "asdsd", 213, 2132, "asdsda"));
        s.add(new Service("asadwqdad", "asdadsqwedsa", "asdsd", 213, 2132, "asdsda"));
        s.add(new Service("asaasdasdd", "asdaasd12asddsdsa", "asdsd", 213, 2132, "asdsda"));
        s.add(new Service("asgasdbsfd", "asdaaw123qeqdsdsa", "asdsd", 213, 2132, "asdsda"));
        System.out.println("services size = " + services.size());
        services.addAll(s);
        mNestedPresenters.get(0).swapDataSet(services);
    }

    @Override
    public void showInsurances(List<Insurance> insurances) {
        mNestedPresenters.get(1).swapDataSet(insurances);
    }

    @Override
    public void showRefueling(List<Refueling> refuelings) {
        mNestedPresenters.get(2).swapDataSet(refuelings);
    }
}
