package elsys.mycar.mycarpro.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;

import elsys.mycar.mycarpro.data.api.ServiceApi;
import elsys.mycar.mycarpro.data.api.UserApi;
import elsys.mycar.mycarpro.data.api.VehicleApi;
import elsys.mycar.mycarpro.data.repository.activities.service.ServiceRepositoryImpl;
import elsys.mycar.mycarpro.data.repository.user.UserRepositoryImpl;
import elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepositoryImpl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProviderUtils {

    public static VehicleRepositoryImpl getVehicleRepository(String token) {
        return new VehicleRepositoryImpl(getVehicleApi(getRetrofit(token)));
    }

    public static ServiceRepositoryImpl getServiceRepository(String token) {
        return new ServiceRepositoryImpl(getServiceApi(getRetrofit(token)), token);
    }

    public static UserRepositoryImpl getUserRepository() {
        return new UserRepositoryImpl(getUserApi(getRetrofit("")));
    }

    public static SharedPreferences getTokenSharedPreferences(String name, Activity activity) {
        return activity.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    private static UserApi getUserApi(Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }

    private static ServiceApi getServiceApi(Retrofit retrofit) {
        return retrofit.create(ServiceApi.class);
    }

    private static VehicleApi getVehicleApi(Retrofit retrofit) {
        return retrofit.create(VehicleApi.class);
    }

    private static Retrofit getRetrofit(final String token) {
        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader(AuthenticationUtils.TOKEN, token).build();
                return chain.proceed(request);
            }
        }).build();

        return new Retrofit.Builder()
                .baseUrl("http://myprocar.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
    }
}
