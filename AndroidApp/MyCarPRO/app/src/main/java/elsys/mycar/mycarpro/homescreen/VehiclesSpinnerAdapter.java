package elsys.mycar.mycarpro.homescreen;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.google.common.collect.HashBiMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class VehiclesSpinnerAdapter extends ArrayAdapter<String> {

    private List<String> mVehicleIds;

    public VehiclesSpinnerAdapter(@NonNull Context context, @LayoutRes int resource, List<String> vehicleIds) {
        super(context, resource);
        mVehicleIds = vehicleIds;
    }

    public void replaceData(@NonNull HashMap<String, String> hashMap) {
        mVehicleIds = new ArrayList<>(hashMap.keySet());
        clear();
        addAll(hashMap.values());
        notifyDataSetChanged();
    }

    public String getVehicleIdAtPosition(int position) {
        return mVehicleIds.get(position);
    }
}
