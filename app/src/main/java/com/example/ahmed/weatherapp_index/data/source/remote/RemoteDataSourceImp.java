package com.example.ahmed.weatherapp_index.data.source.remote;

import android.support.annotation.NonNull;

import com.example.ahmed.weatherapp_index.data.source.remote.api.WeatherApiClient;
import com.example.ahmed.weatherapp_index.data.source.remote.api.WeatherApiService;
import com.example.ahmed.weatherapp_index.data.source.remote.api.response.ForecastResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ahmed on 2/5/18.
 */

public class RemoteDataSourceImp implements RemoteDataSource {

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
    public void getForecastByCityId(int cityId) {

    }

    @Override
    public void getCurrentCityForecastByCoordinates(double latitude, double longitude, final GetCurrentCityForecastCallback callback) {

        weatherApiService.getForecast(latitude, longitude).enqueue(new Callback<ForecastResponse>() {
            @Override
            public void onResponse(@NonNull Call<ForecastResponse> call, @NonNull Response<ForecastResponse> response) {
                callback.onForecastLoaded(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<ForecastResponse> call, @NonNull Throwable t) {
                callback.onDataNotAvailable();
            }
        });

    }
}
