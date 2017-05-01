package elsys.mycar.mycarpro.data;

import java.sql.Ref;
import java.util.HashMap;
import java.util.List;

import elsys.mycar.mycarpro.model.Insurance;
import elsys.mycar.mycarpro.model.Refueling;
import elsys.mycar.mycarpro.model.Service;
import elsys.mycar.mycarpro.model.Vehicle;

public interface VehicleRepository {

    interface IDK {

        void saveVehicle(Vehicle vehicle);

        void updateVehicle(Vehicle vehicle);

        void deleteVehicle(String id);

        void getVehicleById(String id);

        void getVehicles();


        void saveRefueling(String vehicleId, Refueling refueling);

        void updateRefueling(String vehicleId, Refueling refueling);

        void saveService(String vehicleId, Service service);

        void updateService(String vehicleId, Service service);

        void saveInsurance(String vehicleId, Insurance insurance);

        void updateInsurance(String vehicleId, Insurance insurance);

    }

    interface VehiclesCallback {

        void onVehicleSaved(Vehicle vehicle);

        void onVehiclesAvailable(List<Vehicle> vehicles);

        void onFailure();
    }

    interface ServicesCallback {

        void onServiceSaved(Service service);

        void onServicesAvailable(List<Service> services);

        void onFailure();
    }

    interface RefuelingsCallback {

        void onRefuelingSaved(Refueling refueling);

        void onRefuelingsAvailable(List<Refueling> refuelings);

        void onFailure();
    }

    interface InsurancesCallback {

        void onInsuranceSaved(Insurance insurance);

        void onInsurancesAvailable(List<Insurance> insurances);

        void onFailure();
    }























    void save(Vehicle vehicle);

    Vehicle getById(String id);

    void delete(String id);

    List<Vehicle> getAll();

    HashMap<String, String> getVehicleIdToNameHash();

    String getVehicleIdByName(String name);

    String getVehicleNamesById(String id);

    void addInsurance(String vehicleId, Insurance insurance);

    void addService(String vehicleId, Service service);

    void addRefueling(String vehicleId, Refueling refueling);

    List<Service> getServicesByVehicleId(String vehicleId);

    List<Insurance> getInsurancesByVehicleId(String vehicleId);

    List<Refueling> getRefuelingsByVehicleId(String vehicleId);

    List<String> getMakes();

    List<String> getCompanyNames();

    List<String> getServiceTypes();

    List<String> getGasStations();
}
