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

    public VehiclesSpinnerAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull HashMap<String, String> hashMap) {
        super(context, resource, new ArrayList<>(hashMap.values()));
        mVehicleIds = new ArrayList<>(hashMap.keySet());
    }

    public String getVehicleIdByPosition(int position) {
        return mVehicleIds.get(position);
    }
}
