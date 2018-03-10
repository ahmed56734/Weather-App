package com.example.ahmed.weatherapp_index.ui.cities;

import com.example.ahmed.weatherapp_index.data.ModelLayer;
import com.example.ahmed.weatherapp_index.data.ModelLayerImp;
import com.example.ahmed.weatherapp_index.data.models.UserCity;
import com.google.android.gms.location.places.PlaceDetectionClient;

/**
 * Created by ahmed on 2/7/18.
 */

public class CitiesPresenter implements CitiesContract.Presenter {
    private static  CitiesPresenter ourInstance ;

    private ModelLayer mModelLayer;

    public static CitiesPresenter getInstance(ModelLayer modelLayer) {

        if(ourInstance == null)
            ourInstance =  new CitiesPresenter(modelLayer);

        return ourInstance;
    }

    private CitiesPresenter(ModelLayer modelLayer) {
        this.mModelLayer = modelLayer;
    }


    public void getCurrentCityFromGps(PlaceDetectionClient placeDetectionClient, CitiesContract.AddCityCallback callback) {

        mModelLayer.getCurrentLocation(placeDetectionClient, callback);
    }

    public void getCities(CitiesContract.CitiesResultCallback callback) {
        mModelLayer.getAllCities(callback);
    }

    public void deleteCity(UserCity city) {
        mModelLayer.deleteCity(city);
    }

    public void addCity(UserCity userCity, CitiesContract.AddCityCallback callback) {
        mModelLayer.addCity(userCity, callback);

    }


}
