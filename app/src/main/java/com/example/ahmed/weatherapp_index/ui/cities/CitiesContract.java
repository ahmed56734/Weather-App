package com.example.ahmed.weatherapp_index.ui.cities;

import com.example.ahmed.weatherapp_index.data.models.UserCity;

import java.util.List;

/**
 * Created by ahmed on 2/7/18.
 */

public interface CitiesContract {

    interface CitiesResultCallback{

        void onDataLoaded(List<UserCity> result);

        void onDataNotAvailable();
    }

    interface GetCityResultCallback{
        void onDataLoaded(UserCity result);

        void onDataNotAvailable();
    }

    interface CurrentLocationCallback{
        void onLocationUpdated();
    }

    interface AddCityCallback{
        void onCityAdded();
        void onError();
    }

    interface Presenter{

    }



    interface View{

    }
}
