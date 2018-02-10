package com.example.ahmed.weatherapp_index.data;

import com.example.ahmed.weatherapp_index.data.models.Forecast;
import com.example.ahmed.weatherapp_index.data.models.UserCity;
import com.example.ahmed.weatherapp_index.data.models.Weather;
import com.example.ahmed.weatherapp_index.data.source.remote.RemoteDataSource;
import com.example.ahmed.weatherapp_index.data.source.remote.api.response.ForecastResponse;
import com.example.ahmed.weatherapp_index.ui.cities.CitiesContract;
import com.google.android.gms.location.places.PlaceDetectionClient;


import java.util.List;

/**
 * Created by ahmed on 2/5/18.
 */

public interface ModelLayer {


    interface GetCityForecastCallback {

        void onForecastLoaded(List<Forecast> forecastList);

        void onDataNotAvailable();

    }

    void getCurrentLocation(PlaceDetectionClient placeDetectionClient, final CitiesContract.AddCityCallback callback);

    void addCity(UserCity city, CitiesContract.AddCityCallback callback);

    void getAllCities(CitiesContract.CitiesResultCallback callback);

    void deleteCity(UserCity city);




}
