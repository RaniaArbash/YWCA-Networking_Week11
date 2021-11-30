package com.example.networking_week11;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonService {


    public ArrayList<City> parseCitiesAPIJson(String jsonCities){
        //
        ArrayList<City> allCitiesFromAPI = new ArrayList<>(0);
        try {//
            JSONArray jsonArray = new JSONArray(jsonCities);
            for (int i = 0 ; i< jsonArray.length(); i++){
//                String cityName = jsonArray.getString(i);
//                City newCity = new City(jsonArray.getString(i));
                allCitiesFromAPI.add(new City(jsonArray.getString(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return allCitiesFromAPI;
    }

    public WeatherData parseWeatherAPIData(String jsonWeatherString){
        WeatherData weatherData = new WeatherData();
        try {
            JSONObject jsonObject = new JSONObject(jsonWeatherString);// root
            JSONArray weatherArray = jsonObject.getJSONArray("weather");
            JSONObject weatherObject = weatherArray.getJSONObject(0);
            String des = weatherObject.getString("description");
            String icon = weatherObject.getString("icon");
            JSONObject mainObject = jsonObject.getJSONObject("main");
            Double temp = mainObject.getDouble("temp");
            weatherData = new WeatherData(temp,des,icon);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  weatherData;
    }

    //
}
