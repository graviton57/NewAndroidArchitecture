package com.havrylyuk.newandroidarchitecture.data.remote;

import com.havrylyuk.newandroidarchitecture.data.remote.model.CountriesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Igor Havrylyuk on 08.06.2017.
 */

public interface CountriesService {

    @GET("countryInfoJSON")
    Observable<CountriesResponse> getCountries();

}
