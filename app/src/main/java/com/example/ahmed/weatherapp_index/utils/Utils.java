package com.example.ahmed.weatherapp_index.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

/**
 * Created by ahmed on 2/9/18.
 */

public class Utils {


    public static String getWeatherIconUrl(String icon){

        return  Uri.parse("http://openweathermap.org/img/w")
                .buildUpon()
                .appendPath(icon + ".png")
                .build()
                .toString();

    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
