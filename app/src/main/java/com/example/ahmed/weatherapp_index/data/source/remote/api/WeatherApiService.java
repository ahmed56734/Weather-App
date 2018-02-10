package com.example.ahmed.weatherapp_index.data.source.remote.api;



import com.example.ahmed.weatherapp_index.data.source.remote.api.response.ForecastResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ahmed on 2/5/18.
 */

public interface WeatherApiService {

    @GET("forecast?units=metric")
    Call<ForecastResponse> getForecast(@Query("id") int cityId);

    @GET("forecast?units=metric")
    Call<ForecastResponse> getForecast(@Query("lat") double lat, @Query("lon") double lon);


}
