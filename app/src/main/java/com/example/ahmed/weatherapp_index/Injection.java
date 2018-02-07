package com.example.ahmed.weatherapp_index;

import com.example.ahmed.weatherapp_index.data.ModelLayer;
import com.example.ahmed.weatherapp_index.data.ModelLayerImp;
import com.example.ahmed.weatherapp_index.data.source.remote.RemoteDataSource;
import com.example.ahmed.weatherapp_index.data.source.remote.RemoteDataSourceImp;
import com.example.ahmed.weatherapp_index.data.source.remote.api.WeatherApiClient;
import com.example.ahmed.weatherapp_index.data.source.remote.api.WeatherApiService;

/**
 * Created by ahmed on 2/6/18.
 */

public class Injection {

    public static ModelLayer provideModelLayer(){
        WeatherApiService weatherApiService = WeatherApiClient.getClient().create(WeatherApiService.class);
        RemoteDataSource remoteDataSource = RemoteDataSourceImp.getInstance(weatherApiService);

        return ModelLayerImp.getInstance(remoteDataSource);
    }
}
