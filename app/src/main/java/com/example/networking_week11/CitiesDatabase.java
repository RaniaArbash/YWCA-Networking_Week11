package com.example.networking_week11;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(version = 1,entities = {City.class})
public abstract class CitiesDatabase extends RoomDatabase {
    abstract CityDAO getDao();
}
