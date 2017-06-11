package elsys.mycar.mycarpro.data.repository.activities.refueling;

import elsys.mycar.mycarpro.data.model.Refueling;
import elsys.mycar.mycarpro.data.repository.OnItemFetchedCallback;
import elsys.mycar.mycarpro.data.repository.OnItemsFetchedCallback;
import elsys.mycar.mycarpro.data.repository.OnSaveUpdateDeleteCallback;

public class RefuelingRepositoryImpl implements RefuelingRepository {

    @Override
    public void saveRefueling(Refueling refueling, OnSaveUpdateDeleteCallback callback) {

    }

    @Override
    public void updateRefueling(String refuelingId, Refueling refueling, OnSaveUpdateDeleteCallback callback) {

    }

    @Override
    public void deleteRefueling(String refuelingId, OnSaveUpdateDeleteCallback callback) {

    }

    @Override
    public void fetchRefuelingById(String refuelingId, OnItemFetchedCallback<Refueling> callback) {

    }

    @Override
    public void fetchRefuelings(String vehicleId, OnItemsFetchedCallback<Refueling> callback) {

    }
}
