package com.example.viswanathms.asignment3;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by viswanathms on 2018-03-08.
 * This class helps in encapsulating the components of the weather shown in user interface
 * @version 1.0
 * @author viswanath ms
 */

public class WeatherComponents {
    /**
     * Stores the name of the city for which the look up is done
     * */
    private String cityName;
    /**
     * Represents the current temperature at the given place.
     * */
    private float temperature;
    /**
     * Stores the minimum temperature of the place.
     * */
    private float minTemp;
    /**
     * Stores the maximum temperature of the place
     * */
    private float maxTemp;
    /**
     * Stores the cloud percentage in the place.
     * */
    private float cloud;
    /**
     * Stores the type of weather sunny/clear/snow/rain etc.,
     * */
    private String weatherType;
    /**
     * Stores the full description of the weather type
     * */
    private String fullDesc;

    //Getters and Setters
    /**
     * Returns the name of the city for which weather is looked up.
     * @return String name of the city
     * */
    public String getCityName() {
        return cityName;
    }
    /**
     * Assigns the name of the city for which weather is looked up
     * @param cityName String
     * */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    /**
     * Returns the current temperature in the given city
     * @return float current temperature in the given city
     * */
    public float getTemperature() {
        return temperature;
    }
    /**
     * Assigns the current temperature in the given city
     * @param temperature float
     * */
    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
    /**
     * Returns the minimum temperature in the given city
     * @return float minimum temperature in the given city
     * */
    public float getMinTemp() {
        return minTemp;
    }
    /**
     * Assigns the minimum temperature in the given city
     * @param minTemp float
     * */
    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }
    /**
     * Returns the maximum temperature in the given city
     * @return float maximum temperature in the given city
     * */
    public float getMaxTemp() {
        return maxTemp;
    }
    /**
     * Assigns the maximum temperature in the given city
     * @param maxTemp float
     * */
    public void setMaxTemp(float maxTemp) {
        this.maxTemp = maxTemp;
    }
    /**
     * Returns the amount of cloud in the given city
     * @return float amount of cloud in the given city
     * */

    public float getCloud() {
        return cloud;
    }
    /**
     * Assigns the amount of cloud in the given city
     * @param cloud float
     * */
    public void setCloud(float cloud) {
        this.cloud = cloud;
    }
     /**
      * Returns the type of weather in the given city
     * @return float type of weather in the given city
     * */
    public String getWeatherType() {
        return weatherType;
    }
    /**
     * Assigns the type of weather in the given city
     * @param weatherType String
     * */
    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }
    /**
     * Returns a short description of the type of weather in the given city
     * @return float short description of the type of weather in the given city
     * */
    public String getFullDesc() {
        return fullDesc;
    }
    /**
     * Assigns a short description of the  type of weather in the given city
     * @param fullDesc String
     * */
    public void setFullDesc(String fullDesc) {
        this.fullDesc = fullDesc;
    }
}