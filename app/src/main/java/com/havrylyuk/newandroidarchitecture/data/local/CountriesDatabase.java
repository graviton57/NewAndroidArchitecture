package com.havrylyuk.newandroidarchitecture.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.havrylyuk.newandroidarchitecture.data.local.dao.CountryDao;
import com.havrylyuk.newandroidarchitecture.data.entiry.Country;

/**
 * Created by Igor Havrylyuk on 08.06.2017.
 */

@Database(entities = {Country.class}, version = 1)
public abstract class CountriesDatabase extends RoomDatabase {

    public abstract CountryDao countryDao();

    /** The only instance */
    private static CountriesDatabase sInstance;

    /**
     * Gets the singleton instance of SampleDatabase.
     *
     * @param context The context.
     * @return The singleton instance of SampleDatabase.
     */
    public static synchronized CountriesDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room
                    .databaseBuilder(context.getApplicationContext(),
                            CountriesDatabase.class,
                            "countries.db")
                    .build();
        }
        return sInstance;
    }
}
