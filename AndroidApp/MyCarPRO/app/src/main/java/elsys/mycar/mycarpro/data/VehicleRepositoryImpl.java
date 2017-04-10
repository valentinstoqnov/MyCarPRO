package elsys.mycar.mycarpro.data;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import elsys.mycar.mycarpro.model.Vehicle;
import elsys.mycar.mycarpro.util.DateUtils;

public class VehicleRepositoryImpl implements VehicleRepository {

    private List<Vehicle> mVehicles;
    private static final VehicleRepositoryImpl INSTANCE = new VehicleRepositoryImpl();

    private VehicleRepositoryImpl() {
        this.mVehicles = new ArrayList<>();
    }

    public static VehicleRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void save(Vehicle vehicle) {
        mVehicles.add(vehicle);
    }

    @Override
    public List<Vehicle> getAll() {
        List<Vehicle> list = new ArrayList<>();
        String date = DateUtils.textDateFromInts(2015, 3, 2);

        for (int i = 0; i < 5 ; i++) {
            Vehicle v = new Vehicle("Some name" + i, "saasd", "asd", date, 12, 12, "KURVIII");
            list.add(v);
        }
        return list;
    }
}