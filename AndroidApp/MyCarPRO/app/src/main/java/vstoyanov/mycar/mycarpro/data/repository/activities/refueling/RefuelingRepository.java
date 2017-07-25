package vstoyanov.mycar.mycarpro.data.repository.activities.refueling;

import vstoyanov.mycar.mycarpro.data.model.Refueling;
import vstoyanov.mycar.mycarpro.data.repository.OnItemFetchedCallback;
import vstoyanov.mycar.mycarpro.data.repository.OnItemsFetchedCallback;
import vstoyanov.mycar.mycarpro.data.repository.OnSaveUpdateDeleteCallback;

public interface RefuelingRepository {

    void saveRefueling(Refueling refueling, OnSaveUpdateDeleteCallback callback);

    void updateRefueling(String refuelingId, Refueling refueling, OnSaveUpdateDeleteCallback callback);

    void deleteRefueling(String refuelingId, OnSaveUpdateDeleteCallback callback);

    void fetchRefuelingById(String refuelingId, OnItemFetchedCallback<Refueling> callback);

    void fetchRefuelings(String vehicleId, OnItemsFetchedCallback<Refueling> callback);
}
