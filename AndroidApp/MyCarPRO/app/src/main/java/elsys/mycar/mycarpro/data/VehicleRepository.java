package elsys.mycar.mycarpro.data;

import java.util.List;

import elsys.mycar.mycarpro.model.Insurance;
import elsys.mycar.mycarpro.model.Refueling;
import elsys.mycar.mycarpro.model.Service;
import elsys.mycar.mycarpro.model.Vehicle;

public interface VehicleRepository {

    void save(Vehicle vehicle);

    Vehicle getById(String id);

    void delete(String id);

    List<Vehicle> getAll();

    List<String> getAllVehicleNames();

    String getVehicleIdByName(String name);

    void addInsurance(String vehicleId, Insurance insurance);

    void addService(String vehicleId, Service service);

    void addRefueling(String vehicleId, Refueling refueling);

    List<Service> getServicesByVehicleId(String vehicleId);

    List<String> getMakes();

    List<String> getCompanyNames();

    List<String> getServiceTypes();

    List<String> getGasStations();
}
