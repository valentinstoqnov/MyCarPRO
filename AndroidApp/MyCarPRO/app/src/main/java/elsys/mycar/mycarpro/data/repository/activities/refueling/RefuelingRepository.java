package elsys.mycar.mycarpro.data.repository.activities.refueling;

import elsys.mycar.mycarpro.data.model.Refueling;
import elsys.mycar.mycarpro.data.repository.OnSaveUpdateDeleteCallback;

/**
 * Created by valio_stoyanov on 01.05.17.
 */

public interface RefuelingRepository {

    void saveRefueling(String vehicleId, Refueling refueling, OnSaveUpdateDeleteCallback callback);

    void updateRefueling(String vehicleId, String refuelingId, Refueling refueling, OnSaveUpdateDeleteCallback callback);
}
