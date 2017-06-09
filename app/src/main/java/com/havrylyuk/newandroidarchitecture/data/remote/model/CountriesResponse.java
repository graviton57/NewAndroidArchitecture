package com.havrylyuk.newandroidarchitecture.data.remote.model;


import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.havrylyuk.newandroidarchitecture.data.entiry.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor Havrylyuk on 08.03.2017.
 */
public class CountriesResponse extends ApiResponse {

    @SerializedName("geonames")
    private List<Country> countries;

    @NonNull
    public List<Country> getCountries() {
        if (countries==null){
            countries= new ArrayList<>();
        }
        return countries;
    }
}
