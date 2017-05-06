package elsys.mycar.mycarpro.data.repository.activities.insurance;

import elsys.mycar.mycarpro.data.model.Insurance;

public interface InsuranceRepository {

    void saveInsurance(String vehicleId, Insurance insurance);

    void updateInsurance(String vehicleId, Insurance insurance);
}
