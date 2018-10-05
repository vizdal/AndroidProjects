package com.example.viswanathms.asignment3;

import java.util.HashMap;

/**
 * Crearted by viswanathms on 2018-03-08.
 * File that stores a list of application level constants
 */


public class Constants {
    /**
     * Map Stores a list of identified weather conditions and their respective image names
     * This is used to avoid if conditions in the code.
     * */
    public static final HashMap<String,Integer> weatherMap= createWeatherMap();
    public static HashMap<String,Integer> createWeatherMap(){
        HashMap<String,Integer> weatherMap = new HashMap<>();
        weatherMap.put("snow",R.drawable.snow);
        weatherMap.put("rain",R.drawable.rain);
        weatherMap.put("clouds",R.drawable.cloudy);
        weatherMap.put("clear",R.drawable.clear);
        weatherMap.put("sunny",R.drawable.sunny);
        weatherMap.put("mist",R.drawable.mist);
        weatherMap.put("haze",R.drawable.haze);
        weatherMap.put("fog",R.drawable.fog);
        return weatherMap;
    }
}
