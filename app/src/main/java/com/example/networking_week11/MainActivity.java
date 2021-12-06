package com.example.networking_week11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements DatabaseService.DatabaseListener, dbCitiesAdapter.cityClickListner{


    DatabaseService dbService;
    dbCitiesAdapter adapter;
    RecyclerView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbService = ((myApp)getApplication()).getDbService();

        dbService.getDbInstance(this);

        dbService.getAllCitiesFromDB();
        dbService.listener = this;
        // there is one issue in this line.
        //dbService.dbInstance.getDao().getAllCities();

        list = findViewById(R.id.dbcities);
        list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new dbCitiesAdapter(this,new ArrayList<>(0));
        adapter.listner = this;

    }


    public void addNewCity(View view) {

        Intent searchIntent = new Intent(this, SearchActivity.class);
        startActivity(searchIntent);

    }

    @Override
    public void databaseCitiesListener(List<City> dbcities) {
        // cities adapter.
        adapter.cityList = dbcities;
        adapter.notifyDataSetChanged();

    }

    @Override
    public void cityClicked(City selectedCity) {
        Intent intent = new Intent(this,WeatherActivity.class);
        intent.putExtra("SelectedCity",selectedCity.getCityName());
        startActivity(intent);
    }
}