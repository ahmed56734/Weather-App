package com.example.ahmed.weatherapp_index.ui.cityforecast;

import android.os.Bundle;
import android.util.Log;

import com.example.ahmed.weatherapp_index.data.ModelLayer;
import com.example.ahmed.weatherapp_index.data.ModelLayerImp;
import com.example.ahmed.weatherapp_index.data.models.Forecast;
import com.example.ahmed.weatherapp_index.ui.cities.MainActivity;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by ahmed on 2/9/18.
 */

public class CityForecastPresenter implements CityForecastContract.Presenter {

    private static CityForecastPresenter ourInstance;
    private static final String TAG = "CityForecastPresenter";

    private String cityName = "ismailia, Eg";
    private LatLng latLng = new LatLng(32, 32);

    private ModelLayerImp mModelLayer;
    private CityForecastContract.View mCityForecastView;

    static CityForecastPresenter getInstance(ModelLayer modelLayer, CityForecastContract.View view) {

        if (ourInstance == null)
            ourInstance = new CityForecastPresenter(modelLayer, view);

        return ourInstance;
    }

    private CityForecastPresenter(ModelLayer modelLayer, CityForecastContract.View view) {
        this.mModelLayer = (ModelLayerImp) modelLayer;
        this.mCityForecastView = view;
    }


    public void getForecast(final CityForecastContract.UpdateForecastCallback callback) {


        mModelLayer.getCityForecast(latLng.latitude, latLng.longitude, new ModelLayer.GetCityForecastCallback() {
            @Override
            public void onForecastLoaded(List<Forecast> forecastList) {

                Log.d(TAG, "onForecastLoaded");
                callback.onForecastLoaded(cityName, filterForecastList(forecastList));


            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });

    }

    //gets only five different days forecast
    private List<Forecast> filterForecastList(List<Forecast> forecastList) {

        List<Forecast> filteredList = new ArrayList<>();

        filteredList.add(forecastList.get(0));

        for (int i = 1, j = 0; i < forecastList.size(); i++) {

            Forecast forecast = forecastList.get(i);

            if (!forecast.getDayNum().equals(filteredList.get(j).getDayNum())) {
                filteredList.add(forecast);
                j++;
            }
        }

        return filteredList;
    }

    public void parseIntentBundle(Bundle bundle) {

        checkNotNull(bundle);
        cityName = bundle.getString(MainActivity.CITY_NAME_KEY);
        latLng = bundle.getParcelable(MainActivity.CITY_LAT_LNG_KEY);

    }


}
