package com.havrylyuk.newandroidarchitecture;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Igor Havrylyuk on 08.06.2017.
 */

public class CountriesApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
