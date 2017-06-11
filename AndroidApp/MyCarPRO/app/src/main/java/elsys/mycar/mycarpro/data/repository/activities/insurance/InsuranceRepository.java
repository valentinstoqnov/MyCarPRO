package elsys.mycar.mycarpro.data.repository.activities.insurance;

import elsys.mycar.mycarpro.data.model.Insurance;
import elsys.mycar.mycarpro.data.repository.OnItemFetchedCallback;
import elsys.mycar.mycarpro.data.repository.OnItemsFetchedCallback;
import elsys.mycar.mycarpro.data.repository.OnSaveUpdateDeleteCallback;

public interface InsuranceRepository {

    void saveInsurance(Insurance insurance, OnSaveUpdateDeleteCallback callback);

    void updateInsurance(String insuranceId, Insurance insurance, OnSaveUpdateDeleteCallback callback);

    void deleteInsurance(String insuranceId, OnSaveUpdateDeleteCallback callback);

    void fetchInsuranceById(String insuranceId, OnItemFetchedCallback<Insurance> callback);

    void fetchInsurances(String vehicleId, OnItemsFetchedCallback<Insurance> callback);

}
