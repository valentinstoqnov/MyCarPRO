package elsys.mycar.mycarpro.data.repository.activities.insurance;

import elsys.mycar.mycarpro.data.model.Insurance;
import elsys.mycar.mycarpro.data.repository.OnSaveUpdateDeleteCallback;

public interface InsuranceRepository {

    void saveInsurance(String vehicleId, Insurance insurance, OnSaveUpdateDeleteCallback callback);

    void updateInsurance(String vehicleId, String insuranceId, Insurance insurance, OnSaveUpdateDeleteCallback callback);
}
