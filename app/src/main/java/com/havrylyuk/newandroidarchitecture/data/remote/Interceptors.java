package com.havrylyuk.newandroidarchitecture.data.remote;

import android.support.annotation.NonNull;

import com.havrylyuk.newandroidarchitecture.BuildConfig;

import java.io.IOException;
import java.util.Locale;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Igor Havrylyuk on 08.06.2017.
 */

class Interceptors implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder()
                .addQueryParameter("lang", Locale.getDefault().getLanguage())
                .addQueryParameter("username", BuildConfig.GEONAME_API_KEY)
                .addQueryParameter("style", "FULL")
                .build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
