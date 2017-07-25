package vstoyanov.mycar.mycarpro.list.vehicles;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vstoyanov.mycar.mycarpro.R;
import vstoyanov.mycar.mycarpro.data.Constants;
import vstoyanov.mycar.mycarpro.data.model.Vehicle;
import vstoyanov.mycar.mycarpro.detail.vehicle.DetailVehicleActivity;

public class ListVehicleFragment extends Fragment implements ListVehicleContract.View {

    public static final String TAG = "ListVehicleFragment";

    @BindView(R.id.tv_no_vehicles_added) protected TextView tvNoVehiclesAdded;
    @BindView(R.id.rv_list_vehicles) protected RecyclerView recyclerView;

    private ListVehicleContract.Presenter mPresenter;
    private ListVehicleAdapter mAdapter;
    private Unbinder mUnbinder;

    public static ListVehicleFragment newInstance() {
        return new ListVehicleFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_vehicle, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setUpRecyclerView();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab_main);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide(true);
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    fab.show(true);
                }
            }
        });
    }

    @Override
    public void setPresenter(ListVehicleContract.Presenter presenter) {
        this.mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showVehicles(List<Vehicle> vehicles) {
        tvNoVehiclesAdded.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        mAdapter.addVehicles(vehicles);
    }

    @Override
    public void showProgress() {
        // TODO: 19.06.17
    }

    @Override
    public void hideProgress() {
        // TODO: 19.06.17  
    }

    @Override
    public void showVehiclesFetchError() {
        recyclerView.setVisibility(View.GONE);
        tvNoVehiclesAdded.setVisibility(View.GONE);
        Toast.makeText(getContext(), R.string.vehicles_retrieval_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDetailVehicleUi(String vehicleId) {
        Intent intent = new Intent(getContext(), DetailVehicleActivity.class);
        intent.putExtra(Constants.VEHICLE_ID, vehicleId);
        startActivity(intent);
    }

    @Override
    public void showNoVehiclesFound() {
        recyclerView.setVisibility(View.GONE);
        tvNoVehiclesAdded.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    private void setUpRecyclerView() {
        String format = getString(R.string.card_vehicle_info_placeholder);
        mAdapter = new ListVehicleAdapter(new ArrayList<>(0), vehicle -> mPresenter.openVehicleDetails(vehicle), format);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }
}
