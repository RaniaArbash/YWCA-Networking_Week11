package com.example.networking_week11;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class City {// Tabel

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String cityName;// Toronto , On,Canada


    City(){}
    public City(String city){
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

