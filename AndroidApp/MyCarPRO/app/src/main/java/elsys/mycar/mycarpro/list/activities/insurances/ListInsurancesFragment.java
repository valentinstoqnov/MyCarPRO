package elsys.mycar.mycarpro.list.activities.insurances;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.list.activities.ListActivitiesAdapter;
import elsys.mycar.mycarpro.list.activities.ListActivitiesContract;
import elsys.mycar.mycarpro.list.activities.RecyclerViewDivider;
import elsys.mycar.mycarpro.model.Insurance;

public class ListInsurancesFragment extends Fragment implements ListInsurancesContract.View{

    @BindView(R.id.rv_list) RecyclerView recyclerView;
    @BindView(R.id.textView_list) TextView textViewMessage;
    @BindString(R.string.date_price_placeholder) String placeholder;

    private ListActivitiesAdapter mAdapter;
    private ListActivitiesContract.Presenter mPresenter;
    private Unbinder mUnbinder;

    public static ListInsurancesFragment newInstance() {
        return new ListInsurancesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setUpRecyclerView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void setPresenter(ListActivitiesContract.Presenter presenter) {
        this.mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void addItems(List<Insurance> items) {
        if (recyclerView.getVisibility() == View.GONE) {
            recyclerView.setVisibility(View.VISIBLE);
            textViewMessage.setVisibility(View.GONE);
        }
        mAdapter.addInsurances(items);
    }

    @Override
    public void showNoItemsFound() {
        recyclerView.setVisibility(View.GONE);
        String message = getString(R.string.no_items_found);
        textViewMessage.setVisibility(View.VISIBLE);
        textViewMessage.setText(message);
    }

    @Override
    public void showNoSuchVehicle() {
        recyclerView.setVisibility(View.GONE);
        String message = getString(R.string.no_vehicle_found);
        textViewMessage.setVisibility(View.VISIBLE);
        textViewMessage.setText(message);
    }

    private void setUpRecyclerView() {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.line_divider);
        RecyclerViewDivider divider = new RecyclerViewDivider(drawable);
        recyclerView.addItemDecoration(divider);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ListActivitiesAdapter(placeholder, "lv.", R.drawable.ic_insurance);
        mAdapter.setInsurances(new ArrayList<Insurance>());
        recyclerView.setAdapter(mAdapter);
    }
}
