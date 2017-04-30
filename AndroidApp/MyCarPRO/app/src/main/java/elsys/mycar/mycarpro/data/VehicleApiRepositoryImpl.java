package elsys.mycar.mycarpro.data;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.ResponseHandlerInterface;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;
import elsys.mycar.mycarpro.data.api.VehicleApi;
import elsys.mycar.mycarpro.model.Insurance;
import elsys.mycar.mycarpro.model.Refueling;
import elsys.mycar.mycarpro.model.Service;
import elsys.mycar.mycarpro.model.Vehicle;

public class VehicleApiRepositoryImpl implements VehicleRepository {
//http://damp-temple-63875.herokuapp.com/cars

    private VehicleApi mVehicleApi;


    @Override
    public void save(Vehicle vehicle) {
    }

    @Override
    public Vehicle getById(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<Vehicle> getAll() {
        return null;
    }

    @Override
    public HashMap<String, String> getVehicleIdToNameHash() {
        return null;
    }

    @Override
    public String getVehicleIdByName(String name) {
        return null;
    }

    @Override
    public String getVehicleNamesById(String id) {
        return null;
    }

    @Override
    public void addInsurance(String vehicleId, Insurance insurance) {

    }

    @Override
    public void addService(String vehicleId, Service service) {

    }

    @Override
    public void addRefueling(String vehicleId, Refueling refueling) {

    }

    @Override
    public List<Service> getServicesByVehicleId(String vehicleId) {
        return null;
    }

    @Override
    public List<Insurance> getInsurancesByVehicleId(String vehicleId) {
        return null;
    }

    @Override
    public List<Refueling> getRefuelingsByVehicleId(String vehicleId) {
        return null;
    }

    @Override
    public List<String> getMakes() {
        return null;
    }

    @Override
    public List<String> getCompanyNames() {
        return null;
    }

    @Override
    public List<String> getServiceTypes() {
        return null;
    }

    @Override
    public List<String> getGasStations() {
        return null;
    }
}
