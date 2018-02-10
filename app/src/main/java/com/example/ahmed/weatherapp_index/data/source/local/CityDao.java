package com.example.ahmed.weatherapp_index.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.ahmed.weatherapp_index.data.models.UserCity;

import java.util.List;

/**
 * Created by ahmed on 2/10/18.
 */

@Dao
public interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertCity(UserCity city);

    @Update
    public int updateCity(UserCity... cities);

    @Delete
    public int deleteCities(UserCity... cities);


    @Query("SELECT * FROM UserCity WHERE placeId != :id")
    public List<UserCity> getAllCitiesExceptCurrentCity(String id);




    @Query("SELECT * FROM UserCity WHERE placeId = :id")
    public UserCity getCityById(String id);


}
