package elsys.mycar.mycarpro.detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import elsys.mycar.mycarpro.R;

public class DetailVehicleFragment extends Fragment {

    public static DetailVehicleFragment newInstance() {
        
        Bundle args = new Bundle();
        
        DetailVehicleFragment fragment = new DetailVehicleFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_vehicle, container, false);
    }

}
