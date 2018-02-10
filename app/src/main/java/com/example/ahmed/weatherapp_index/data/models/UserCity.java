package com.example.ahmed.weatherapp_index.data.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;



/**
 * Created by ahmed on 2/10/18.
 */

@Entity
public class UserCity  {

    @PrimaryKey
    @NonNull
    private String placeId;

    private String name;

    private double latitude;
    private double longitude;

    @Ignore
    private List<Forecast> forecastList;




    public UserCity(String placeId, String name, double latitude, double longitude) {
        this.placeId = placeId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public UserCity(String placeId, String name, double latitude, double longitude, List<Forecast> forecastList) {
        this(placeId, name, latitude, longitude);
        this.forecastList = forecastList;
    }

    public String getPlaceId() {
        return placeId;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
