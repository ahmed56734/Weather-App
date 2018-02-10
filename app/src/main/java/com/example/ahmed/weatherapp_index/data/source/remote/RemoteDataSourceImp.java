package com.example.ahmed.weatherapp_index.data.source.remote;

import android.support.annotation.NonNull;
import android.util.Log;


import com.example.ahmed.weatherapp_index.data.source.remote.api.WeatherApiService;
import com.example.ahmed.weatherapp_index.data.source.remote.api.response.ForecastResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmed on 2/5/18.
 */

public class RemoteDataSourceImp implements RemoteDataSource {
    private static final String TAG = "RemoteDataSourceImp";

    private static RemoteDataSource INSTANCE;

    private WeatherApiService weatherApiService;

    private RemoteDataSourceImp(WeatherApiService weatherApiService) {

        this.weatherApiService = weatherApiService;
    }

    public static RemoteDataSource getInstance(@NonNull WeatherApiService weatherApiService) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSourceImp(weatherApiService);
        }
        return INSTANCE;
    }



    @Override
    public void getCityForecastByCoordinates(double latitude, double longitude, final ApiForecastResponse callback) {



        weatherApiService.getForecast(latitude, longitude).enqueue(new Callback<ForecastResponse>() {
            @Override
            public void onResponse(@NonNull Call<ForecastResponse> call, @NonNull Response<ForecastResponse> response) {
                Log.d(TAG, "onResponse");
                callback.onForecastLoaded(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<ForecastResponse> call, @NonNull Throwable t) {
                Log.e("api failure", "error", t);
                callback.onDataNotAvailable();
            }
        });

    }
}
