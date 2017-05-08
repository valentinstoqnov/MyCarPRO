package elsys.mycar.mycarpro.data.repository.activities.insurance;

import elsys.mycar.mycarpro.data.model.Insurance;
import elsys.mycar.mycarpro.data.repository.OnSaveOrUpdateCallback;

public interface InsuranceRepository {

    void saveInsurance(String vehicleId, Insurance insurance, OnSaveOrUpdateCallback<Insurance> callback);

    void updateInsurance(String vehicleId, String insuranceId, Insurance insurance, OnSaveOrUpdateCallback<Insurance> callback);
}
