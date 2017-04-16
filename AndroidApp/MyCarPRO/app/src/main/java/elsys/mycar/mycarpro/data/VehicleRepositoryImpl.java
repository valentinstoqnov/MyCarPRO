package elsys.mycar.mycarpro.data;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import elsys.mycar.mycarpro.model.Insurance;
import elsys.mycar.mycarpro.model.Refueling;
import elsys.mycar.mycarpro.model.Service;
import elsys.mycar.mycarpro.model.Vehicle;
import elsys.mycar.mycarpro.util.DateUtils;
import elsys.mycar.mycarpro.util.StringUtils;

public class VehicleRepositoryImpl implements VehicleRepository {

    private List<Vehicle> mVehicles;
    private static final VehicleRepositoryImpl INSTANCE = new VehicleRepositoryImpl();

    private VehicleRepositoryImpl() {
        this.mVehicles = new ArrayList<>();
        String date = DateUtils.textDateFromInts(2015, 3, 2);
        for (int i = 0; i < 5 ; i++) {
            Vehicle v = new Vehicle(UUID.randomUUID().toString(), "Some name" + i, "saasd", "asd", date, 12, 12, "KURVIII");
            v.setInsurances(new ArrayList<Insurance>());
            v.setServices(new ArrayList<Service>());
            v.setRefuelings(new ArrayList<Refueling>());
            mVehicles.add(v);
        }
    }

    public static VehicleRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void save(Vehicle vehicle) {
        if (!StringUtils.checkNotNullOrEmpty(vehicle.getId())) {
            vehicle.setId(UUID.randomUUID().toString());
        }
        vehicle.setInsurances(new ArrayList<Insurance>());
        vehicle.setServices(new ArrayList<Service>());
        vehicle.setRefuelings(new ArrayList<Refueling>());
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
        return mVehicles;
    }

    @Override
    public List<String> getAllVehicleNames() {
        List<String> names = new ArrayList<>();

        for (Vehicle vehicle : mVehicles) {
            names.add(vehicle.getName());
        }

        Collections.sort(names);
        return names;
    }

    @Override
    public String getVehicleIdByName(String name) {
        for (Vehicle vehicle : mVehicles) {
            if (vehicle.getName().equals(name)) {
                return vehicle.getId();
            }
        }

        return null;
    }

    @Override
    public void addInsurance(String vehicleId, Insurance insurance) {
        for (Vehicle vehicle : mVehicles) {
            if (vehicle.getId().equals(vehicleId)) {
                vehicle.addInsurance(insurance);
            }
        }
    }

    @Override
    public void addService(String vehicleId, Service service) {
        for (Vehicle vehicle : mVehicles) {
            if (vehicle.getId().equals(vehicleId)) {
                vehicle.addService(service);
            }
        }
    }

    @Override
    public void addRefueling(String vehicleId, Refueling refueling) {
        for (Vehicle vehicle : mVehicles) {
            if (vehicle.getId().equals(vehicleId)) {
                vehicle.addRefueling(refueling);
            }
        }
    }
}