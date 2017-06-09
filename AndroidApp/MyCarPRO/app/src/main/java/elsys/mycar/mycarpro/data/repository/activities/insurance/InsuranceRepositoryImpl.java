package elsys.mycar.mycarpro.data.repository.activities.insurance;

import com.google.gson.Gson;

import elsys.mycar.mycarpro.data.api.InsuranceApi;
import elsys.mycar.mycarpro.data.model.Insurance;
import elsys.mycar.mycarpro.data.repository.OnSaveUpdateDeleteCallback;
import retrofit2.Call;

public class InsuranceRepositoryImpl implements InsuranceRepository {
    @Override
    public void saveInsurance(String vehicleId, Insurance insurance, OnSaveUpdateDeleteCallback callback) {

    }

    @Override
    public void updateInsurance(String vehicleId, String insuranceId, Insurance insurance, OnSaveUpdateDeleteCallback callback) {

    }

   /* private InsuranceApi mInsuranceApi;
    private String mToken;

    public InsuranceRepositoryImpl(InsuranceApi mInsuranceApi, String mToken) {
        this.mInsuranceApi = mInsuranceApi;
        this.mToken = mToken;
    }

    @Override
    public void saveInsurance(String vehicleId, Insurance insurance, OnSaveUpdateDeleteCallback<Insurance> callback) {
        String s = new Gson().toJson(insurance);
        System.out.println("parsed object = " + s);
        Call<Insurance> insuranceCall = mInsuranceApi.saveInsurance(vehicleId, insurance);
        insuranceCall.enqueue(ActivitiesRepositoryUtils.provideSaveUpdateCallback4Retrofit(callback));
    }

    @Override
    public void updateInsurance(String vehicleId, String insuranceId, Insurance insurance, OnSaveUpdateDeleteCallback<Insurance> callback) {
        insurance.setId(insuranceId);
        Call<Insurance> insuranceCall = mInsuranceApi.updateInsurance(vehicleId, insurance);
        insuranceCall.enqueue(ActivitiesRepositoryUtils.provideSaveUpdateCallback4Retrofit(callback));
    }*/
}
