package com.example.networking_week11;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WeatherActivity extends AppCompatActivity implements NetworkingService.NetworkingListener{


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
        networkingService.listener = this;

        String cityName = getIntent().getStringExtra("SelectedCity");
        networkingService.fetchWeatherData(cityName);
        cityText = findViewById(R.id.cityName);
        cityText.setText(cityName);
        weatherText = findViewById(R.id.weather);
        imageView = findViewById(R.id.image);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void APINetworkListner(String jsonString) {
        WeatherData weatherData = jsonService.parseWeatherAPIData(jsonString);
        weatherText.setText(weatherData.description + " : "+weatherData.temp );
        networkingService.getImageData(weatherData.icon);
    }

    @Override
    public void APINetworkingListerForImage(Bitmap image) {
        imageView.setImageBitmap(image);
    }
}
