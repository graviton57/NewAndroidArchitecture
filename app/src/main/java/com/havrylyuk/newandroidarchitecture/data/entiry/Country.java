package com.havrylyuk.newandroidarchitecture.data.entiry;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Igor Havrylyuk on 08.03.2017.
 */

@Entity(tableName = "countries")
public class Country implements Parcelable{

    private String continent;
    private String capital;
    private String languages;
    @PrimaryKey
    private int    geonameId;
    private float  south;
    private String isoAlpha3;
    private float north;
    private String fipsCode;
    private int population;
    private float  east;
    private String isoNumeric;
    private String areaInSqKm;
    private String countryCode;
    private float  west;
    private String countryName;
    private String continentName;
    private String currencyCode;

    public Country() {
    }

    protected Country(Parcel in) {
        continent = in.readString();
        capital = in.readString();
        languages = in.readString();
        geonameId = in.readInt();
        south = in.readFloat();
        isoAlpha3 = in.readString();
        north = in.readFloat();
        fipsCode = in.readString();
        population = in.readInt();
        east = in.readFloat();
        isoNumeric = in.readString();
        areaInSqKm = in.readString();
        countryCode = in.readString();
        west = in.readFloat();
        countryName = in.readString();
        continentName = in.readString();
        currencyCode = in.readString();
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public int getGeonameId() {
        return geonameId;
    }

    public void setGeonameId(int geonameId) {
        this.geonameId = geonameId;
    }

    public float getSouth() {
        return south;
    }

    public void setSouth(float south) {
        this.south = south;
    }

    public String getIsoAlpha3() {
        return isoAlpha3;
    }

    public void setIsoAlpha3(String isoAlpha3) {
        this.isoAlpha3 = isoAlpha3;
    }

    public float getNorth() {
        return north;
    }

    public void setNorth(float north) {
        this.north = north;
    }

    public String getFipsCode() {
        return fipsCode;
    }

    public void setFipsCode(String fipsCode) {
        this.fipsCode = fipsCode;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public float getEast() {
        return east;
    }

    public void setEast(float east) {
        this.east = east;
    }

    public String getIsoNumeric() {
        return isoNumeric;
    }

    public void setIsoNumeric(String isoNumeric) {
        this.isoNumeric = isoNumeric;
    }

    public String getAreaInSqKm() {
        return areaInSqKm;
    }

    public void setAreaInSqKm(String areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public float getWest() {
        return west;
    }

    public void setWest(float west) {
        this.west = west;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(continent);
        dest.writeString(capital);
        dest.writeString(languages);
        dest.writeInt(geonameId);
        dest.writeFloat(south);
        dest.writeString(isoAlpha3);
        dest.writeFloat(north);
        dest.writeString(fipsCode);
        dest.writeInt(population);
        dest.writeFloat(east);
        dest.writeString(isoNumeric);
        dest.writeString(areaInSqKm);
        dest.writeString(countryCode);
        dest.writeFloat(west);
        dest.writeString(countryName);
        dest.writeString(continentName);
        dest.writeString(currencyCode);
    }
}
