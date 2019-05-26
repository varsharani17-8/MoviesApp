package mymovies.com.mytestapplication.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import mymovies.com.mytestapplication.utility.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    static ApiEndpointInterface apiService;
    static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(getClient())
            .build();

    public static ApiEndpointInterface getService() {
        if (apiService != null) {
            return apiService;
        } else {
            apiService = retrofit.create(ApiEndpointInterface.class);
            return apiService;
        }
    }

    private static OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        return client;
    }





}
