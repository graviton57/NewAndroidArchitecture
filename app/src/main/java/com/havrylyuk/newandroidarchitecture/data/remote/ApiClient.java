package com.havrylyuk.newandroidarchitecture.data.remote;

import android.support.annotation.NonNull;

import com.havrylyuk.newandroidarchitecture.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Igor Havrylyuk on 08.06.2017.
 */

public final class ApiClient {

    private static volatile OkHttpClient client;

    private static volatile CountriesService service;

    @NonNull
    public static CountriesService getCountriesService() {
        CountriesService service = ApiClient.service;
        if (service == null) {
            synchronized (ApiClient.class) {
                service = ApiClient.service;
                if (service == null) {
                    service = ApiClient.service = createService();
                }
            }
        }
        return service;
    }

    @NonNull
    private static CountriesService createService() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_GEONAME_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(CountriesService.class);
    }

    @NonNull
    private static OkHttpClient getClient() {
        OkHttpClient client = ApiClient.client;
        if (client == null) {
            synchronized (ApiClient.class) {
                client = ApiClient.client;
                if (client == null) {
                    client = ApiClient.client = buildClient();
                }
            }
        }
        return client;
    }

    @NonNull
    private static OkHttpClient buildClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new Interceptors())
                .build();
    }
}
