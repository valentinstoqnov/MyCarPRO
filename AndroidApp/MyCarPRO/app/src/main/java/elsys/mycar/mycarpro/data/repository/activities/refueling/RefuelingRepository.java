package elsys.mycar.mycarpro.data.repository.activities.refueling;

import elsys.mycar.mycarpro.data.model.Refueling;

/**
 * Created by valio_stoyanov on 01.05.17.
 */

public interface RefuelingRepository {

    void saveRefueling(String vehicleId, Refueling refueling);

    void updateRefueling(String vehicleId, Refueling refueling);
}
