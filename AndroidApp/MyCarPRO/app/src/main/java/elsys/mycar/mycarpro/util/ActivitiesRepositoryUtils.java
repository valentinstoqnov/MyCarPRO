package elsys.mycar.mycarpro.util;

import elsys.mycar.mycarpro.data.repository.OnSaveOrUpdateCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitiesRepositoryUtils {

    public static <T> Callback<T> provideSaveUpdateCallback4Retrofit(final OnSaveOrUpdateCallback<T> callback) {
        return new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                callback.onFailure();
                t.printStackTrace();
            }
        };
    }
}
