package elsys.mycar.mycarpro.data.repository.activities.refueling;

import elsys.mycar.mycarpro.data.api.RefuelingApi;
import elsys.mycar.mycarpro.data.repository.OnSaveOrUpdateCallback;
import elsys.mycar.mycarpro.data.model.Refueling;
import elsys.mycar.mycarpro.util.ActivitiesRepositoryUtils;
import retrofit2.Call;

public class RefuelingRepositoryImpl implements RefuelingRepository {

    private RefuelingApi mRefuelingApi;
    private OnSaveOrUpdateCallback<Refueling> mCallback;

    @Override
    public void saveRefueling(String vehicleId, final Refueling refueling) {
        Call<Refueling> call = mRefuelingApi.saveRefueling(vehicleId, refueling);
        call.enqueue(ActivitiesRepositoryUtils.provideSaveUpdateCallback4Retrofit(mCallback));
    }

    @Override
    public void updateRefueling(String vehicleId, Refueling refueling) {
        Call<Refueling> call = mRefuelingApi.updateRefueling(vehicleId, refueling);
        call.enqueue(ActivitiesRepositoryUtils.provideSaveUpdateCallback4Retrofit(mCallback));
    }
}
