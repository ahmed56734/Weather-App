package com.example.ahmed.weatherapp_index.ui.cityforecast;

import com.example.ahmed.weatherapp_index.data.models.Forecast;

import java.util.List;

/**
 * Created by ahmed on 2/9/18.
 */

public interface CityForecastContract {


    interface UpdateForecastCallback{

        void onForecastLoaded(String cityName, List<Forecast> forecastList);
        void onDataNotAvailable();

    }

    interface Presenter {

    }

    interface View {
        void updateForecast(String cityName, List<Forecast> forecastList);

    }
}
