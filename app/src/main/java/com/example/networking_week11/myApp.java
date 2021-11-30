package com.example.networking_week11;

import android.app.Application;

public class myApp extends Application {

   public NetworkingService getNetworkingService() {
      return networkingService;
   }


   private NetworkingService networkingService = new NetworkingService();
   private JsonService jsonService = new JsonService();

   public JsonService getJsonService() {
      return jsonService;
   }
}
