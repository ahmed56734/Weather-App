package com.example.ahmed.weatherapp_index.data.source.remote;

import com.example.ahmed.weatherapp_index.data.source.remote.api.response.ForecastResponse;

/**
 * Created by ahmed on 2/5/18.
 */

public interface RemoteDataSource {


    interface ApiForecastResponse {

        void onForecastLoaded(ForecastResponse response);

        void onDataNotAvailable();
    }


    void getCityForecastByCoordinates(double latitude, double longitude, final ApiForecastResponse callback);





}
