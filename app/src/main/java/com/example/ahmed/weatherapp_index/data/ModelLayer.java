package com.example.ahmed.weatherapp_index.data;

import com.example.ahmed.weatherapp_index.data.models.Weather;
import com.example.ahmed.weatherapp_index.data.source.remote.RemoteDataSource;


import java.util.List;

/**
 * Created by ahmed on 2/5/18.
 */

public interface ModelLayer {


    interface GetCityForecastCallback {

        void onForecastLoaded(List<Weather> response);

        void onDataNotAvailable();
    }


    void getCurrentCityForecast(double latitude, double longitude,
                                RemoteDataSource.GetCurrentCityForecastCallback callback);

}
