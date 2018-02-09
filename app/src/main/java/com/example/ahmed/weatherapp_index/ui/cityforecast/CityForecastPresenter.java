package com.example.ahmed.weatherapp_index.ui.cityforecast;

import com.example.ahmed.weatherapp_index.data.ModelLayer;
import com.example.ahmed.weatherapp_index.data.ModelLayerImp;
import com.example.ahmed.weatherapp_index.ui.cities.CitiesPresenter;

/**
 * Created by ahmed on 2/9/18.
 */

public class CityForecastPresenter implements CityForecastContract.Presenter {

    private static CityForecastPresenter ourInstance ;

    private String cityName;
    private double latitude;
    private double longitude;

    private ModelLayerImp mModelLayer;

    public static CityForecastPresenter getInstance(ModelLayer modelLayer) {

        if(ourInstance == null)
            ourInstance =  new CityForecastPresenter(modelLayer);

        return ourInstance;
    }

    private CityForecastPresenter(ModelLayer modelLayer){
        this.mModelLayer = (ModelLayerImp) modelLayer;
    }


}
