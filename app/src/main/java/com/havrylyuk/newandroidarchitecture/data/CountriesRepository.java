package com.havrylyuk.newandroidarchitecture.data;

import android.support.annotation.NonNull;

import com.havrylyuk.newandroidarchitecture.data.local.dao.CountryDao;
import com.havrylyuk.newandroidarchitecture.data.remote.CountriesService;
import com.havrylyuk.newandroidarchitecture.data.remote.model.CountriesResponse;
import com.havrylyuk.newandroidarchitecture.data.entiry.Country;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Igor Havrylyuk on 08.06.2017.
 */

public class CountriesRepository {

    @NonNull
    private final CountriesService service;

    @NonNull
    private final CountryDao countryDao;

    public CountriesRepository(@NonNull CountriesService service,
                               @NonNull CountryDao countryDao) {
        this.service = service;
        this.countryDao = countryDao;
    }

    @NonNull
    public Observable<List<Country>> getCountries() {
        return service.getCountries()
                .map(CountriesResponse::getCountries)
                .doOnNext(countryDao::insertAll)
                .onErrorResumeNext(throwable -> {
                    List<Country> countries = countryDao.loadAllCountries();
                    return Observable.just(countries);
                });
    }
}
