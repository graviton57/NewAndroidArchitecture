package com.havrylyuk.newandroidarchitecture.ui.base;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Igor Havrylyuk on 08.06.2017.
 */

@SuppressLint("Registered")
public class BaseLifecycleActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    @NonNull
    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @MainThread
    @NonNull
    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
