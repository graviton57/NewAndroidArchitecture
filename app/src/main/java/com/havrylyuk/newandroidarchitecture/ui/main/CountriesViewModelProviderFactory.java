package com.havrylyuk.newandroidarchitecture.ui.main;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.havrylyuk.newandroidarchitecture.data.CountriesRepository;
import com.havrylyuk.newandroidarchitecture.data.local.dao.CountryDao;
import com.havrylyuk.newandroidarchitecture.data.remote.CountriesService;
import com.havrylyuk.newandroidarchitecture.ui.base.BaseViewModelFactory;


/**
 * Created by Igor Havrylyuk on 08.06.2017.
 */

class CountriesViewModelProviderFactory extends BaseViewModelFactory {

    @NonNull
    private final CountriesRepository repository;

    CountriesViewModelProviderFactory(@NonNull CountriesService moviesService,
                                      @NonNull CountryDao moviesDao) {
        this.repository = new CountriesRepository(moviesService, moviesDao);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.equals(CountriesViewModel.class)) {
            //noinspection unchecked
            return (T) new CountriesViewModel(repository);
        }
        return super.create(modelClass);
    }
}
