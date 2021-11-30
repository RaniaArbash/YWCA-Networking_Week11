package com.example.networking_week11;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WeatherActivity extends AppCompatActivity {


    TextView cityText;
    TextView weatherText;
    ImageView imageView;
    NetworkingService networkingService;
    JsonService jsonService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        networkingService = ( (myApp)getApplication()).getNetworkingService();
        jsonService = ( (myApp)getApplication()).getJsonService();


        String cityName = getIntent().getStringExtra("SelectedCity");



//
//        cityText = findViewById(R.id.cityName);
//        weatherText = findViewById(R.id.weather);
//        imageView = findViewById(R.id.image);
//        cityText.setText(cityName);


    }

}
