package com.example.networking_week11;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CityDAO {
    @Insert
    void addNewCity(City c);
//
//    @Insert
//    void addTwoCities(City c1, City c2);
//
//    @Insert
//    void addAllCities(List<City> list);


    @Delete
    void deleteCity(City c);

    @Query("SELECT * FROM City")
    List<City> getAllCities();



}
