package com.example.ahmed.weatherapp_index.data;

import android.support.annotation.NonNull;

import com.example.ahmed.weatherapp_index.data.source.remote.RemoteDataSource;
import com.example.ahmed.weatherapp_index.data.source.remote.RemoteDataSourceImp;
import com.example.ahmed.weatherapp_index.data.source.remote.api.WeatherApiClient;
import com.example.ahmed.weatherapp_index.data.source.remote.api.WeatherApiService;

import retrofit2.Retrofit;

/**
 * Created by ahmed on 2/5/18.
 */

public class ModelLayerImp implements ModelLayer {

    private static ModelLayer INSTANCE;

    private RemoteDataSource remoteDataSource;


    private ModelLayerImp(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static ModelLayer getInstance(@NonNull RemoteDataSource remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new ModelLayerImp(remoteDataSource);
        }
        return INSTANCE;
    }


    public void getCurrentCityForecast(double latitude, double longitude,
                                       RemoteDataSource.GetCurrentCityForecastCallback callback){

        remoteDataSource.getCurrentCityForecastByCoordinates(latitude, longitude, callback);

    }

}
