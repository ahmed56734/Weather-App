package com.example.ahmed.weatherapp_index.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.ahmed.weatherapp_index.data.models.UserCity;

/**
 * Created by ahmed on 2/10/18.
 */

@Database(entities = {UserCity.class}, version = 1)
public abstract class WeatherDatabase extends RoomDatabase {

    private static WeatherDatabase INSTANCE;

    public abstract CityDao taskDao();

    private static final Object sLock = new Object();

    public static WeatherDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        WeatherDatabase.class, "weatherapp.db")
                        .build();
            }
            return INSTANCE;
        }
    }
}
