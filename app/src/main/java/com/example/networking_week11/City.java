package com.example.networking_week11;




public class City {
    private int id;
    private String cityName;// Toronto , On,Canada


    City(){}
    public City(String city, String country){
        this.cityName = city;
    }


    public int getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}

