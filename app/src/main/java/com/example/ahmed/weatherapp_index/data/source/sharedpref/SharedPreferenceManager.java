package com.example.ahmed.weatherapp_index.data.source.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.example.ahmed.weatherapp_index.data.ModelLayer;
import com.example.ahmed.weatherapp_index.data.ModelLayerImp;
import com.example.ahmed.weatherapp_index.data.source.local.LocalDataSource;
import com.example.ahmed.weatherapp_index.data.source.remote.RemoteDataSource;

/**
 * Created by ahmed on 2/10/18.
 */

public class SharedPreferenceManager {

    private static SharedPreferenceManager INSTANCE;
    private SharedPreferences sharedPreferences;
    private static final String CURRENT_CITY_ID_KEY = "1331";
    public static final String DEFAULT_CITY_ID = "ChIJdd4hrwug2EcRmSrV3Vo6llI";


    private Context mContext;


    private SharedPreferenceManager( Context context) {
        this.mContext = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }
    private SharedPreferenceManager(){};

    public static SharedPreferenceManager getInstance(Context context) {
        if (INSTANCE == null) {
            return new SharedPreferenceManager(context);
        }
        return INSTANCE;
    }



    public void saveCurrentCityId(String id){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CURRENT_CITY_ID_KEY, id);
        editor.commit();
    }

    public String getCurrentCityIdKey(){
        return sharedPreferences.getString(CURRENT_CITY_ID_KEY, DEFAULT_CITY_ID);
    }

}
