package elsys.mycar.mycarpro.data.repository.activities.refueling;

import elsys.mycar.mycarpro.data.api.RefuelingApi;
import elsys.mycar.mycarpro.data.model.Refueling;
import elsys.mycar.mycarpro.data.repository.OnSaveUpdateDeleteCallback;
import retrofit2.Call;

public class RefuelingRepositoryImpl implements RefuelingRepository {
    @Override
    public void saveRefueling(String vehicleId, Refueling refueling, OnSaveUpdateDeleteCallback callback) {

    }

    @Override
    public void updateRefueling(String vehicleId, String refuelingId, Refueling refueling, OnSaveUpdateDeleteCallback callback) {

    }

  /*  private RefuelingApi mRefuelingApi;
    private String mToken;

    public RefuelingRepositoryImpl(RefuelingApi mRefuelingApi, String mToken) {
        this.mRefuelingApi = mRefuelingApi;
        this.mToken = mToken;
    }

    @Override
    public void saveRefueling(String vehicleId, final Refueling refueling, OnSaveUpdateDeleteCallback<Refueling> callback) {
        //refueling.setId(UUID.randomUUID().toString());
        Call<Refueling> call = mRefuelingApi.saveRefueling(vehicleId, refueling);
        call.enqueue(ActivitiesRepositoryUtils.provideSaveUpdateCallback4Retrofit(callback));
    }

    @Override
    public void updateRefueling(String vehicleId, String refuelingId, Refueling refueling, OnSaveUpdateDeleteCallback<Refueling> callback) {
        refueling.setId(refuelingId);
        Call<Refueling> call = mRefuelingApi.updateRefueling(vehicleId, refueling);
        call.enqueue(ActivitiesRepositoryUtils.provideSaveUpdateCallback4Retrofit(callback));
    }*/
}
