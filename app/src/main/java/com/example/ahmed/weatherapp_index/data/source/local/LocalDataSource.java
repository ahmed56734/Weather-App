package com.example.ahmed.weatherapp_index.data.source.local;

import com.example.ahmed.weatherapp_index.data.models.UserCity;
import com.example.ahmed.weatherapp_index.ui.cities.CitiesContract;

/**
 * Created by ahmed on 2/11/18.
 */

public interface LocalDataSource {
    void deleteCity(UserCity city);

    void getAllCities(String currentCityId, CitiesContract.CitiesResultCallback callback);

    void addCity(UserCity city, CitiesContract.AddCityCallback callback);
}
