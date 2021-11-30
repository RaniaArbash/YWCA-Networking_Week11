package com.example.networking_week11;

public class WeatherData {

    Double temp;
    String description;
    String icon;

    public WeatherData() {
    }

    public WeatherData(Double temp, String description, String icon) {
        this.temp = temp;
        this.description = description;
        this.icon = icon;
    }
}
