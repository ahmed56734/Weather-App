package com.example.ahmed.weatherapp_index.ui.cities;

import com.example.ahmed.weatherapp_index.BasePresenter;
import com.example.ahmed.weatherapp_index.BaseView;
import com.example.ahmed.weatherapp_index.data.models.UserCity;
import com.google.android.gms.location.places.PlaceDetectionClient;

import java.util.List;

/**
 * Created by ahmed on 2/7/18.
 */

public interface CitiesContract {

    interface CitiesResultCallback {

        void onDataLoaded(List<UserCity> result);

        void onDataNotAvailable();
    }

    interface GetCityResultCallback {
        void onDataLoaded(UserCity result);

        void onDataNotAvailable();
    }

    interface CurrentLocationCallback {
        void onLocationUpdated();
    }

    interface AddCityCallback {
        void onCityAdded();

        void onError();
    }

    interface Presenter extends BasePresenter {
        void getCurrentCityFromGps(PlaceDetectionClient placeDetectionClient, CitiesContract.AddCityCallback callback);

        void getCities(CitiesContract.CitiesResultCallback callback);

        void deleteCity(UserCity city);

        void addCity(UserCity userCity, CitiesContract.AddCityCallback callback);
    }


    interface View extends BaseView {

    }
}
