package com.example.ahmed.weatherapp_index.data.source.local;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.ahmed.weatherapp_index.data.models.UserCity;
import com.example.ahmed.weatherapp_index.ui.cities.CitiesContract;
import com.example.ahmed.weatherapp_index.utils.AppExecutors;

import java.util.List;

/**
 * Created by ahmed on 2/10/18.
 */

public class LocalDataSourceImp implements LocalDataSource {

    private static final String TAG = "LocalDataSource";
    private static volatile LocalDataSource INSTANCE;

    private AppExecutors appExecutors;
    private CityDao cityDao;


    private LocalDataSourceImp(@NonNull CityDao cityDao, AppExecutors appExecutors) {
        this.cityDao = cityDao;
        this.appExecutors = appExecutors;
    }

    public static LocalDataSource getInstance(@NonNull CityDao cityDao, AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (LocalDataSourceImp.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocalDataSourceImp(cityDao, appExecutors);
                }
            }
        }
        return INSTANCE;
    }




    @Override
    public void deleteCity(final UserCity city) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int i = cityDao.deleteCities(city);

                Log.i(TAG, "deleted cities " + i);
            }
        };

        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getAllCities(final String currentCityId, final CitiesContract.CitiesResultCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                UserCity currentCity = cityDao.getCityById(currentCityId);

                final List<UserCity> result = cityDao.getAllCitiesExceptCurrentCity(currentCityId);

                if (currentCity == null || currentCityId.equals("-1")) {
                    currentCity = new UserCity("ChIJdd4hrwug2EcRmSrV3Vo6llI", "London, UK", 51.5073509, -0.1277583);
                }

                result.add(0, currentCity);

                appExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (!result.isEmpty()) {
                            for (UserCity city : result)
                                Log.i(TAG, "city " + city.getName());
                            callback.onDataLoaded(result);
                        } else
                            callback.onDataNotAvailable();
                    }
                });


            }
        };

        appExecutors.diskIO().execute(runnable);
    }


    @Override
    public void addCity(final UserCity city, final CitiesContract.AddCityCallback callback) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                long i = cityDao.insertCity(city);
                if (i > 0) {

                    Log.i(TAG, "inserted cities " + i);

                    callback.onCityAdded();

                } else
                    callback.onError();
            }
        };

        appExecutors.diskIO().execute(runnable);

    }
}
