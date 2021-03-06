package vstoyanov.mycar.mycarpro.statistics;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vstoyanov.mycar.mycarpro.R;
import vstoyanov.mycar.mycarpro.list.activities.viewpager.ActivitiesViewPagerAdapter;
import vstoyanov.mycar.mycarpro.statistics.table.TableFragment;

public class StatisticsFragment extends Fragment {

    public static final String TAG = "StatisticsFragment";

    @BindView(R.id.view_pager_activities) ViewPager viewPager;

    private TabLayout mTabLayout;
    private Unbinder mUnbinder;

    private TabLayout.OnTabSelectedListener mTabSelectedListener = new TabLayout.OnTabSelectedListener() {
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
    };

    public static StatisticsFragment newInstance() {
        return new StatisticsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activities, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTabLayout = (TabLayout) getActivity().findViewById(R.id.tab_layout_statistics);
        setUpTabLayout();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        mTabLayout.addOnTabSelectedListener(mTabSelectedListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        mTabLayout.removeOnTabSelectedListener(mTabSelectedListener);
    }

    private void setUpTabLayout() {
        ActivitiesViewPagerAdapter adapter = new ActivitiesViewPagerAdapter(getChildFragmentManager());

        adapter.addFragment(new TableFragment());
        adapter.addFragment(new ChartFragment());

        viewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(viewPager);

        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_stats_table).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_statistics).getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
    }
}
