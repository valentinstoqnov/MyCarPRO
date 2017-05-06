package elsys.mycar.mycarpro.data.repository.activities.insurance;

import elsys.mycar.mycarpro.data.api.InsuranceApi;
import elsys.mycar.mycarpro.data.repository.OnSaveOrUpdateCallback;
import elsys.mycar.mycarpro.data.model.Insurance;
import elsys.mycar.mycarpro.util.ActivitiesRepositoryUtils;
import retrofit2.Call;

public class InsuranceRepositoryImpl implements InsuranceRepository {

    private InsuranceApi mInsuranceApi;
    private OnSaveOrUpdateCallback<Insurance> mCallback;

    @Override
    public void saveInsurance(String vehicleId, Insurance insurance) {
        Call<Insurance> insuranceCall = mInsuranceApi.saveInsurance(vehicleId, insurance);
        insuranceCall.enqueue(ActivitiesRepositoryUtils.provideSaveUpdateCallback4Retrofit(mCallback));
    }

    @Override
    public void updateInsurance(String vehicleId, Insurance insurance) {
        Call<Insurance> insuranceCall = mInsuranceApi.updateInsurance(vehicleId, insurance);
        insuranceCall.enqueue(ActivitiesRepositoryUtils.provideSaveUpdateCallback4Retrofit(mCallback));
    }
}
