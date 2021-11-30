package com.example.networking_week11;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkingService {
    String  weatherURL = "https://api.openweathermap.org/data/2.5/weather?q=";
    String weatherURL2 = "&appid=071c3ffca10be01d334505630d2c1a9c";

    String iconURL1 = "https://openweathermap.org/img/wn/";
    String iconURL2 = "@2x.png";

    String url = "http://gd.geobytes.com/AutoCompleteCity?&q=";


    public static final ExecutorService networkingExecutor = Executors.newFixedThreadPool(4);
    static Handler networkHander = new Handler(Looper.getMainLooper());

    interface NetworkingListener{
        void APINetworkListner(String jsonString);
        void APINetworkingListerForImage(Bitmap image);
    }

    NetworkingListener listener;


    public void fetchCitiesData(String text){
        String completeURL = url + text;
        connect(completeURL);
    }

    public void fetchWeatherData(String cityName){
        String completeURL = weatherURL+cityName+weatherURL2;
        connect(completeURL);
    }

    public void getImageData(String icon){
        String completeURL = iconURL1 + icon + iconURL2;
        networkingExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL urlObj = new URL(completeURL);
                    InputStream in = ((InputStream)urlObj.getContent());
                    Bitmap imageData = BitmapFactory.decodeStream(in);
                    networkHander.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.APINetworkingListerForImage(imageData);
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // tor
    private void connect(String url){
        networkingExecutor.execute(new Runnable() {
            String jsonString = "";
            @Override
            public void run() {

                HttpURLConnection httpURLConnection = null;
                try {
                    URL urlObject = new URL(url);
                    httpURLConnection = (HttpURLConnection) urlObject.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("Content-Type","application/json");
                    int statues = httpURLConnection.getResponseCode();

                    if ((statues >= 200) && (statues <= 299)) {
                        InputStream in = httpURLConnection.getInputStream();
                        InputStreamReader inputStreamReader = new InputStreamReader(in);
                        int read = 0;
                        while ((read = inputStreamReader.read()) != -1) {// json integers ASCII
                            char c = (char) read;
                            jsonString += c;
                        }// jsonString = ["Torbert, LA, United States","Torch, OH, United States","Toreboda, VG, Sweden","Torino, PI, Italy","Tornado, WV, United States","Tornillo, TX, United States","Tornio, LP, Finland","Toronto, KS, United States","Toronto, OH, United States","Toronto, ON, Canada","Toronto, SD, United States","Torquay, QL, Australia","Torrance, CA, United States","Torrance, PA, United States","torre del greco, CM, Italy","Torre Pellice, PI, Italy","Torrelles de Llobregat, CT, Spain","TORRENS CREEK, QL, Australia","Torreon, CA, Mexico","Torreon, NM, United States"]
                        // dataTask in ios
                        final String finalJson = jsonString;
                        networkHander.post(new Runnable() {
                            @Override
                            public void run() {
                                //send data to main thread
                                listener.APINetworkListner(finalJson);
                            }
                        });
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    httpURLConnection.disconnect();
                }
            }
        });
    }

}
