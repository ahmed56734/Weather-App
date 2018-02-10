package com.example.ahmed.weatherapp_index;

import android.content.Context;

import com.example.ahmed.weatherapp_index.data.ModelLayer;
import com.example.ahmed.weatherapp_index.data.ModelLayerImp;
import com.example.ahmed.weatherapp_index.data.source.local.LocalDataSource;
import com.example.ahmed.weatherapp_index.data.source.local.WeatherDatabase;
import com.example.ahmed.weatherapp_index.data.source.remote.RemoteDataSource;
import com.example.ahmed.weatherapp_index.data.source.remote.RemoteDataSourceImp;
import com.example.ahmed.weatherapp_index.data.source.remote.api.WeatherApiClient;
import com.example.ahmed.weatherapp_index.data.source.remote.api.WeatherApiService;
import com.example.ahmed.weatherapp_index.data.source.sharedpref.SharedPreferenceManager;
import com.example.ahmed.weatherapp_index.utils.AppExecutors;

/**
 * Created by ahmed on 2/6/18.
 */

public class Injection {

    public static ModelLayer provideModelLayer(Context context) {
        WeatherApiService weatherApiService = WeatherApiClient.getClient().create(WeatherApiService.class);
        RemoteDataSource remoteDataSource = RemoteDataSourceImp.getInstance(weatherApiService);
        WeatherDatabase weatherDatabase = WeatherDatabase.getInstance(context);
        LocalDataSource localDataSource = LocalDataSource.getInstance(weatherDatabase.taskDao(), new AppExecutors());
        SharedPreferenceManager sharedPreferenceManager = SharedPreferenceManager.getInstance(context);

        return ModelLayerImp.getInstance(remoteDataSource, localDataSource,
                sharedPreferenceManager, context);
    }
}
