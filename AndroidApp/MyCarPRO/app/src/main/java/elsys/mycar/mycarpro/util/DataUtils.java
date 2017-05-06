package elsys.mycar.mycarpro.util;

import android.util.Log;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashBiMap;

import java.util.ArrayList;
import java.util.List;

import elsys.mycar.mycarpro.model.Vehicle;

public class DataUtils {

    public static HashBiMap<String, String> getVehicleIdsAndNamesToHash(List<Vehicle> vehicles) {
        HashBiMap<String, String> hash = HashBiMap.create(vehicles.size());

        for (Vehicle vehicle : vehicles) {
            String put = hash.put(vehicle.getId(), vehicle.getName());
            Log.d("PUT:", "val = " + put);
        }

        Log.d("hash process:"," size() = " + hash.size());
        return hash;
    }

    public static List<String> getVehicleNames(List<Vehicle> vehicles) {
        List<String> names = new ArrayList<>(vehicles.size());

        for (Vehicle vehicle : vehicles) {
            names.add(vehicle.getName());
        }

        return names;
    }

    public static List<String> getVehicleIds(List<Vehicle> vehicles) {
        List<String> ids = new ArrayList<>(vehicles.size());

        for (Vehicle vehicle : vehicles) {
            ids.add(vehicle.getId());
        }

        return ids;
    }
}
