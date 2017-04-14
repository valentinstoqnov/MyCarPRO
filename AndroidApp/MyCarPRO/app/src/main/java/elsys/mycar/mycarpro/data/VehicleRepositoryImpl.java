package elsys.mycar.mycarpro.data;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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
        vehicle.setId(UUID.randomUUID().toString());
        mVehicles.add(vehicle);
    }

    @Override
    public Vehicle getById(String id) {
        for (Vehicle vehicle : mVehicles) {
            if (vehicle.getId().equals(id)) {
                return vehicle;
            }
        }
        return null;
    }

    @Override
    public void delete(String id) {
        for (Iterator<Vehicle> iterator = mVehicles.iterator(); iterator.hasNext();) {
            Vehicle vehicle = iterator.next();
            if (vehicle.getId().equals(id)) {
                iterator.remove();
            }
        }
    }

    @Override
    public List<Vehicle> getAll() {
        String date = DateUtils.textDateFromInts(2015, 3, 2);

        for (int i = 0; i < 5 ; i++) {
            Vehicle v = new Vehicle(UUID.randomUUID().toString(), "Some name" + i, "saasd", "asd", date, 12, 12, "KURVIII");
            mVehicles.add(v);
        }

        return mVehicles;
    }
}