package com.havrylyuk.newandroidarchitecture.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.havrylyuk.newandroidarchitecture.data.CountriesRepository;
import com.havrylyuk.newandroidarchitecture.data.entiry.Country;
import com.havrylyuk.newandroidarchitecture.data.remote.response.Response;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Igor Havrylyuk on 08.06.2017.
 */

public class CountriesViewModel extends ViewModel {

    @NonNull
    private final MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();

    @NonNull
    private final CountriesRepository countriesRepository;

    @Nullable
    private MutableLiveData<Response<List<Country>>> countriesLiveData;

    public CountriesViewModel(@NonNull CountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    @NonNull
    LiveData<Boolean> isLoading() {
        return loadingLiveData;
    }

    @MainThread
    @NonNull
    LiveData<Response<List<Country>>> getMoviesList() {
        if (countriesLiveData == null) {
            countriesLiveData = new MutableLiveData<>();
            countriesRepository.getCountries()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(disposable -> loadingLiveData.setValue(true))
                    .doAfterTerminate(() -> loadingLiveData.setValue(false))
                    .subscribe(
                            countries1 -> countriesLiveData.setValue(Response.success(countries1)),
                            throwable -> countriesLiveData.setValue(Response.error(throwable))
                    );
        }
        return countriesLiveData;
    }
}
