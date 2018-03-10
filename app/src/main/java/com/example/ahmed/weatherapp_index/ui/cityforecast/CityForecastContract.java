package com.example.ahmed.weatherapp_index.ui.cityforecast;

import android.os.Bundle;

import com.example.ahmed.weatherapp_index.BasePresenter;
import com.example.ahmed.weatherapp_index.BaseView;
import com.example.ahmed.weatherapp_index.data.models.Forecast;

import java.util.List;

/**
 * Created by ahmed on 2/9/18.
 */

public interface CityForecastContract {


    interface UpdateForecastCallback {

        void onForecastLoaded(String cityName, List<Forecast> forecastList);

        void onDataNotAvailable();

    }

    interface Presenter extends BasePresenter {

        void getForecast(UpdateForecastCallback callback);

        void parseIntentBundle(Bundle extras);
    }

    interface View extends BaseView {
        void updateForecast(String cityName, List<Forecast> forecastList);

    }
}
