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
import elsys.mycar.mycarpro.homescreen.VehiclesSpinnerAdapter;
import elsys.mycar.mycarpro.list.activities.insurances.ListInsurancesFragment;
import elsys.mycar.mycarpro.list.activities.refuelings.ListRefuelingsFragment;
import elsys.mycar.mycarpro.list.activities.services.ListServicesFragment;
import elsys.mycar.mycarpro.list.activities.viewpager.ActivitiesViewPagerAdapter;
import elsys.mycar.mycarpro.list.base.BaseActivitiesPresenter;

public class ActivitiesFragment extends Fragment implements ActivitiesContract.View {

    public static final String TAG = "ActivitiesFragment";

    @BindView(R.id.view_pager_activities) ViewPager viewPager;

    private TabLayout mTabLayout;
    private Unbinder mUnbinder;

    private ActivitiesContract.Presenter mPresenter;
    private List<BaseActivitiesPresenter> mNestedPresenters;

    public static ActivitiesFragment newInstance() {
        return new ActivitiesFragment();
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.fragment_activities, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresenter(ActivitiesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
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
        BaseActivitiesPresenter<Service> servicePresenter = new BaseActivitiesPresenter<>(listServicesFragment, true);
        listServicesFragment.setPresenter(servicePresenter);
        mNestedPresenters.add(servicePresenter);
        adapter.addFragment(listServicesFragment);

        ListInsurancesFragment listInsurancesFragment = new ListInsurancesFragment();
        BaseActivitiesPresenter<Insurance> insurancePresenter = new BaseActivitiesPresenter<>(listInsurancesFragment, true);
        listInsurancesFragment.setPresenter(insurancePresenter);
        mNestedPresenters.add(insurancePresenter);
        adapter.addFragment(listInsurancesFragment);

        ListRefuelingsFragment listRefuelingsFragment = new ListRefuelingsFragment();
        BaseActivitiesPresenter<Refueling> refuelingPresenter = new BaseActivitiesPresenter<>(listRefuelingsFragment, true);
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
        List<Insurance> i = new ArrayList<>();
        i.add(new Insurance("asd", "asdadawqe", 123, 312, "asdasda", "1232asda", "asdasda"));
        i.add(new Insurance("asasdasdasdd", "asdadasdawqe", 1232, 312, "asdasda", "1232asda", "asdasda"));
        i.add(new Insurance("agergsd", "asdadaawdaswqe", 1223, 312, "asdasda", "1232asda", "asdasda"));
        i.add(new Insurance("aasdaxassd", "asdadawdawqe", 1323, 312, "asdasda", "1232asda", "asdasda"));
        i.add(new Insurance("afsdsd", "asdadaawdwqe", 123, 312, "asdasda", "1232asda", "asdasda"));
        i.add(new Insurance("a342sd", "asdawaddawqe", 1223, 312, "asdasda", "1232asda", "asdasda"));
        i.add(new Insurance("assdfd", "asdaasdddawqe", 1323, 312, "asdasda", "1232asda", "asdasda"));
        i.add(new Insurance("ahhsd", "asdadaawdwqe", 1232, 312, "asdasda", "1232asda", "asdasda"));
        insurances.addAll(i);
        mNestedPresenters.get(1).swapDataSet(insurances);
    }

    @Override
    public void showRefueling(List<Refueling> refuelings) {
        List<Refueling> r = new ArrayList<>();
        r.add(new Refueling("asd", "sadsad", "dasd", 2.1, 23, 23, "asda"));
        r.add(new Refueling("asdasda", "sadasdsad", "daergsd", 2.1, 23, 23, "asda"));
        r.add(new Refueling("asdqwre", "sadasdsad", "dagersd", 2.1, 23, 23, "asda"));
        r.add(new Refueling("asasdd", "sadasdsad", "dasasdd", 2.1, 23, 23, "asda"));
        r.add(new Refueling("ashrfgd", "saasddsad", "dsadasd", 2.1, 23, 23, "asda"));
        r.add(new Refueling("aswerd", "sasdadsad", "dasadsd", 2.1, 23, 23, "asda"));
        r.add(new Refueling("ascvxd", "sadasdsad", "daasdsd", 2.1, 23, 23, "asda"));
        r.add(new Refueling("assdfd", "sadasdsad", "daasdsd", 2.1, 23, 23, "asda"));
        refuelings.addAll(r);
        mNestedPresenters.get(2).swapDataSet(refuelings);
    }
}
