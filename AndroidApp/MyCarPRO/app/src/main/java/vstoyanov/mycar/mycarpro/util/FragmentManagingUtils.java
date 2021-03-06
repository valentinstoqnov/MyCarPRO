package vstoyanov.mycar.mycarpro.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

import vstoyanov.mycar.mycarpro.list.activities.ActivitiesFragment;
import vstoyanov.mycar.mycarpro.list.vehicles.ListVehicleFragment;
import vstoyanov.mycar.mycarpro.profile.ProfileFragment;
import vstoyanov.mycar.mycarpro.statistics.StatisticsFragment;

public class FragmentManagingUtils {

    private FragmentManager mFragmentManager;
    private int mContainerId;

    public FragmentManagingUtils(FragmentManager fragmentManager, int containerId) {
        mFragmentManager = fragmentManager;
        mContainerId = containerId;
    }

    private Fragment instantiateFragmentByTag(String tag) {
        switch (tag) {
            case ListVehicleFragment.TAG:
                return ListVehicleFragment.newInstance();
            case ActivitiesFragment.TAG:
                return ActivitiesFragment.newInstance();
            case StatisticsFragment.TAG:
                return StatisticsFragment.newInstance();
            case ProfileFragment.TAG:
                return ProfileFragment.newInstance();
        }

        throw new IllegalArgumentException("Unknown tag");
    }

    public Fragment getFragmentByTag(String tag) {
        return mFragmentManager.findFragmentByTag(tag);
    }

    public Fragment addOrShowFragment(String tag) {
        Fragment fragment = getFragmentByTag(tag);
        hideVisibleFragments();
        if (fragment == null || !fragment.isAdded()) {
            fragment = instantiateFragmentByTag(tag);
            addFragment(fragment, tag);
        }else {
            showFragment(fragment);
        }

        return fragment;
    }

    public void addFragment(Fragment fragment, String tag) {
        mFragmentManager.beginTransaction()
                .add(mContainerId, fragment, tag)
                .commit();
    }

    public void showFragment(Fragment fragmentToShow) {
        if (fragmentToShow != null && !fragmentToShow.isVisible()) {
            mFragmentManager.beginTransaction()
                    .show(fragmentToShow)
                    .commit();
        }
    }

    @SuppressWarnings("RestrictedApi")
    private void hideVisibleFragments() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        List<Fragment> fragments = mFragmentManager.getFragments();

        if (fragments != null && !fragments.isEmpty()) {
            for (Fragment fragment : fragments) {
                if (!fragment.isHidden()) {
                    fragmentTransaction.hide(fragment);
                }
            }
        }

        fragmentTransaction.commit();
    }
}
