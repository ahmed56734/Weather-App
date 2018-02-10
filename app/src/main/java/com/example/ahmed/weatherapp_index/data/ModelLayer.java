package com.example.ahmed.weatherapp_index.data;

import com.example.ahmed.weatherapp_index.data.models.Forecast;
import com.example.ahmed.weatherapp_index.data.models.Weather;
import com.example.ahmed.weatherapp_index.data.source.remote.RemoteDataSource;
import com.example.ahmed.weatherapp_index.data.source.remote.api.response.ForecastResponse;


import java.util.List;

/**
 * Created by ahmed on 2/5/18.
 */

public interface ModelLayer {


    interface GetCityForecastCallback {

        void onForecastLoaded(List<Forecast> forecastList);

        void onDataNotAvailable();

    }




}
