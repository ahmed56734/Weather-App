package com.example.ahmed.weatherapp_index.data.source.remote;

import com.example.ahmed.weatherapp_index.data.source.remote.api.response.ForecastResponse;

/**
 * Created by ahmed on 2/5/18.
 */

public interface RemoteDataSource {


    interface GetCurrentCityForecastCallback {

        void onForecastLoaded(ForecastResponse response);

        void onDataNotAvailable();
    }

    void getForecastByCityId(int cityId);

    void getCurrentCityForecastByCoordinates(double latitude, double longitude, GetCurrentCityForecastCallback callback);


}
