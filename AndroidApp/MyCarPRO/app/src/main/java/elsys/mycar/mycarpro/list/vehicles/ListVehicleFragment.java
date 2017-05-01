package elsys.mycar.mycarpro.list.vehicles;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.google.common.base.Preconditions;

import java.util.List;

import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.detail.DetailVehicleActivity;
import elsys.mycar.mycarpro.model.Vehicle;

public class ListVehicleFragment extends Fragment implements ListVehicleContract.View {

    public static final String TAG = "ListVehicleFragment";

    private RecyclerView recyclerView;
    private ListVehicleContract.Presenter mPresenter;
    private ListVehicleAdapter mAdapter;

    public static ListVehicleFragment newInstance() {
        return new ListVehicleFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_vehicle, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list_vehicles);
        setUpRecyclerView();
        mPresenter.start();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab_main);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.isShown()) {
                    fab.hide(true);
                } else if (dy < 0 && fab.isHidden()) {
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
        mAdapter.addVehicles(vehicles);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    private void setUpRecyclerView() {

        mAdapter = new ListVehicleAdapter(new ListVehicleAdapter.OnCardActionListener() {
            @Override
            public void onViewClick(String vehicleId) {
                Intent intent = new Intent(getContext(), DetailVehicleActivity.class);
                intent.putExtra(DetailVehicleActivity.DETAIL_VEHICLE_ID, vehicleId);
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(String id, int position) {
                mPresenter.deleteVehicle(id);
                mAdapter.removeVehicle(position);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }
}
