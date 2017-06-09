package com.havrylyuk.newandroidarchitecture.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.havrylyuk.newandroidarchitecture.data.entiry.Country;

import java.util.List;

/**
 * Created by Igor Havrylyuk on 08.06.2017.
 */
@Dao
public interface CountryDao {

    @Query("SELECT * FROM countries")
    List<Country> loadAllCountries();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Country> countries);

    @Query("select * from countries where geonameId = :countryId")
    Country loadCountry(int countryId);
}
