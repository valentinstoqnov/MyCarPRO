package elsys.mycar.mycarpro.list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.data.VehicleRepositoryImpl;

public class ListVehicleFragment extends Fragment {

    public static ListVehicleFragment newInstance() {
        return new ListVehicleFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_vehicle, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_list_vehicles);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ListVehicleAdapter adapter = new ListVehicleAdapter();
        adapter.setVehicle(VehicleRepositoryImpl.getInstance().getAll());
        recyclerView.setAdapter(adapter);
        return view;
    }

}
