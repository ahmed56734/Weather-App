package com.example.ahmed.weatherapp_index.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.ahmed.weatherapp_index.data.models.UserCity;
import com.example.ahmed.weatherapp_index.data.source.local.LocalDataSource;
import com.example.ahmed.weatherapp_index.data.source.remote.RemoteDataSource;
import com.example.ahmed.weatherapp_index.data.source.remote.api.response.ForecastResponse;
import com.example.ahmed.weatherapp_index.data.source.sharedpref.SharedPreferenceManager;
import com.example.ahmed.weatherapp_index.ui.cities.CitiesContract;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/**
 * Created by ahmed on 2/5/18.
 */

public class ModelLayerImp implements ModelLayer {

    private static ModelLayer INSTANCE;

    private RemoteDataSource remoteDataSource;
    private LocalDataSource localDataSource;
    private SharedPreferenceManager sharedPreferenceManager;


    private Context mContext;


    private ModelLayerImp(RemoteDataSource remoteDataSource, LocalDataSource localDataSource,
                          SharedPreferenceManager sharedPreferenceManager, Context context) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        this.sharedPreferenceManager = sharedPreferenceManager;
        this.mContext = context;

    }

    public static ModelLayer getInstance(@NonNull RemoteDataSource remoteDataSource,
                                         LocalDataSource localDataSource,
                                         SharedPreferenceManager sharedPreferenceManager,
                                         Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ModelLayerImp(remoteDataSource, localDataSource, sharedPreferenceManager, context);
        }
        return INSTANCE;
    }


    public void getCityForecast(double latitude, double longitude, final GetCityForecastCallback callback) {

        getCityForecastFromRemoteDataSource(latitude, longitude, callback);

    }

    private void getCityForecastFromRemoteDataSource(double latitude, double longitude, final GetCityForecastCallback callback) {
        remoteDataSource.getCityForecastByCoordinates(latitude, longitude, new RemoteDataSource.ApiForecastResponse() {
            @Override
            public void onForecastLoaded(ForecastResponse response) {
                Log.d("model layer", "onForecastLoaded");
                callback.onForecastLoaded(response.getForecast());

            }

            @Override
            public void onDataNotAvailable() {
                Log.d("model layer", "onData no available");
                callback.onDataNotAvailable();
            }
        });
    }

    public void getCurrentLocation(PlaceDetectionClient placeDetectionClient, final CitiesContract.AddCityCallback callback) {

        @SuppressLint("MissingPermission") Task<PlaceLikelihoodBufferResponse> placeResult = placeDetectionClient.getCurrentPlace(null);
        placeResult.addOnCompleteListener(new OnCompleteListener<PlaceLikelihoodBufferResponse>() {
            @Override
            public void onComplete(@NonNull Task<PlaceLikelihoodBufferResponse> task) {
                PlaceLikelihoodBufferResponse likelyPlaces = task.getResult();
                Place place = likelyPlaces.get(0).getPlace();

                UserCity userCity = new UserCity(place.getId(), place.getAddress().toString(), place.getLatLng().latitude, place.getLatLng().longitude, null);
                sharedPreferenceManager.saveCurrentCityId(userCity.getPlaceId());
                addCity(userCity, callback);

                likelyPlaces.release();
            }
        });

    }

    public void addCity(UserCity city, CitiesContract.AddCityCallback callback) {
        localDataSource.addCity(city, callback);
    }


    public void getAllCities(CitiesContract.CitiesResultCallback callback) {
        String id = sharedPreferenceManager.getCurrentCityIdKey();
        localDataSource.getAllCities(id, callback);
    }


    public void deleteCity(UserCity city) {
        localDataSource.deleteCity(city);
    }
}
