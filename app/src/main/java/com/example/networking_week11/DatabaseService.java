package com.example.networking_week11;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseService {

    interface DatabaseListener{
        void databaseCitiesListener(List<City> dbcities);
    }
    public DatabaseListener listener;


    public static CitiesDatabase dbInstance;


    ExecutorService citiesExecutor = Executors.newFixedThreadPool(4);
    Handler citiesHandler = new Handler(Looper.getMainLooper());

    private void buildDB(Context context){
        dbInstance = Room.databaseBuilder(context,
                CitiesDatabase.class, "cities_database").build();
    }


    public CitiesDatabase getDbInstance(Context context){
        if (dbInstance == null)
            buildDB(context);

        return dbInstance;
    }


   void  getAllCitiesFromDB(){
        //dbService.dbInstance.getDao().getAllCities();

        citiesExecutor.execute(new Runnable() {
            @Override
            public void run() {
               List<City> cities = dbInstance.getDao().getAllCities();
               citiesHandler.post(new Runnable() {
                   @Override
                   public void run() {
                       listener.databaseCitiesListener(cities);
                   }
               });
            }
        });
    }

    void saveNewCity(City c){
        citiesExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dbInstance.getDao().addNewCity(c);
            }
        });
    }























}
