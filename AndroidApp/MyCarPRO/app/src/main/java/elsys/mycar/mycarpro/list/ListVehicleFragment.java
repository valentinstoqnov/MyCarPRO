package elsys.mycar.mycarpro.list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.base.Preconditions;

import java.util.List;

import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.data.VehicleRepositoryImpl;
import elsys.mycar.mycarpro.model.Vehicle;

public class ListVehicleFragment extends Fragment implements ListVehicleContract.View {

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
    public void setPresenter(ListVehicleContract.Presenter presenter) {
        this.mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void addVehicles(List<Vehicle> vehicles) {
        mAdapter.addVehicles(vehicles);
    }

    private void setUpRecyclerView() {

        mAdapter = new ListVehicleAdapter(new ListVehicleAdapter.OnCardActionListener() {
            @Override
            public void onViewClick(String vehicleId) {
                //TODO: open bottom sheet stuff code here
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
