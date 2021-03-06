package com.example.networking_week11;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements
        CitiesAdapter.cityClickListner, NetworkingService.NetworkingListener {

    ArrayList<City> cities = new ArrayList<City>();
    RecyclerView recyclerView;
    CitiesAdapter adapter;
    NetworkingService networkingService;
    JsonService jsonService;
    DatabaseService dbService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        dbService = ((myApp)getApplication()).getDbService();

        recyclerView = findViewById(R.id.citiesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CitiesAdapter(this,cities);
        recyclerView.setAdapter(adapter);
        setTitle("Search for new cities..");

        networkingService = ( (myApp)getApplication()).getNetworkingService();
        jsonService = ( (myApp)getApplication()).getJsonService();


        networkingService.listener = this;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchViewMenuItem = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) searchViewMenuItem.getActionView();
        String searchFor = searchView.getQuery().toString();
        if (!searchFor.isEmpty()) {// toronto
            searchView.setIconified(false);
            searchView.setQuery(searchFor, false);
        }

        searchView.setQueryHint("Search for cities");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("query", query);//
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("query change", newText);
                if (newText.length() >= 3) {
                    // seach for city
                    networkingService.fetchCitiesData(newText);
                }
                else {
                    adapter.cityList = new ArrayList<>(0);
                    adapter.notifyDataSetChanged();
                }
                return false;
            }
        });
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        networkingService = ( (myApp)getApplication()).getNetworkingService();
        jsonService = ( (myApp)getApplication()).getJsonService();
        networkingService.listener = this;
    }

    @Override
    public void cityClicked(City selectedCity) {
        // show an alert to ask the usr for saving this city to db
        dbService.saveNewCity(selectedCity);
        finish();
    }

    @Override
    public void APINetworkListner(String jsonString) {
        Log.d("tag", jsonString);// not parsed yet.
        cities =  jsonService.parseCitiesAPIJson(jsonString);
        adapter.cityList = cities;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void APINetworkingListerForImage(Bitmap image) {

    }
}
