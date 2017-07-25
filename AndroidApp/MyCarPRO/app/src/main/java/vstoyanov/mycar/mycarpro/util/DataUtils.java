package vstoyanov.mycar.mycarpro.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import vstoyanov.mycar.mycarpro.data.model.Vehicle;

public class DataUtils {

    public static HashMap<String, String> getVehicleIdsAndNames(List<Vehicle> vehicles) {
        HashMap<String, String> hash = new HashMap<>(vehicles.size());

        for (Vehicle vehicle : vehicles) {
            hash.put(vehicle.getId(), vehicle.getName());
        }

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
