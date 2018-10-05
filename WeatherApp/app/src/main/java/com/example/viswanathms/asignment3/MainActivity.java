package com.example.viswanathms.asignment3;
// Images : https://www.pinterest.ca/pin/31103053657156431/
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import org.json.JSONException;
import org.json.JSONObject;

import static com.google.android.gms.location.places.AutocompleteFilter.TYPE_FILTER_CITIES;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    private static TextView placeName ;
    private static TextView tempratureView ;
    private static TextView weatherCondition ;
    private static TextView minmaxView ;
    private static TextView explanationView ;
    private static TextView humidityView;
    private static TextView cloudView;
    private static RelativeLayout parent;
    private Runnable runnable;
    public static JSONObject result;

    private final WeatherComponents weather = new WeatherComponents();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parent = findViewById(R.id.parent_layout);
        placeName = findViewById(R.id.place_name);
        weatherCondition = findViewById(R.id.weather_condition);
        tempratureView = findViewById(R.id.temprature_view);
        weatherCondition = findViewById(R.id.weather_condition);
        minmaxView = findViewById(R.id.minmax_view);
        explanationView = findViewById(R.id.explanation_view);
        humidityView = findViewById(R.id.humidity_view);
        cloudView = findViewById(R.id.cloud_view);

        //Google Place AutoComplete for selecting cities
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        AutocompleteFilter filter = new AutocompleteFilter.Builder().setTypeFilter(TYPE_FILTER_CITIES).build();
        autocompleteFragment.setFilter(filter);
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName());
                weather.setCityName(String.valueOf(place.getName()));
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        getWeatherResponse("c82a5529f579710701cf785d6a839433", getApplicationContext());
                    }
                };
                Thread thread = new Thread(null, runnable, "background");
                thread.start();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });
    }
    public void getWeatherResponse(String apikey, Context context){
        //https://stackoverflow.com/questions/19477324/how-do-i-calculate-the-temperature-in-celsius-returned-in-openweathermap-org-jso
        String openWeatherUrl = "http://api.openweathermap.org/data/2.5/weather?units=metric&q="+weather.getCityName()+"&appid="+apikey;
        //Implicit GET Request
        JsonObjectRequest openWeatherRequest = new JsonObjectRequest(openWeatherUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                placeName.setText(weather.getCityName());
                try{
                    tempratureView.setText(response.getJSONObject("main").get("temp").toString()+"\u00b0C");
                    String weatherText = ((JSONObject)response.getJSONArray("weather").get(0)).get("main").toString();
                    weatherCondition.setText(weatherText);
                    if(Constants.weatherMap.containsKey(weatherText.toLowerCase())) {
                        parent.setBackground(getResources().getDrawable(Constants.weatherMap.get(weatherText.toLowerCase())));
                    }else{
                        parent.setBackground(getResources().getDrawable(R.drawable.clear));
                    }
                    String max = response.getJSONObject("main").get("temp_max").toString();
                    String min = response.getJSONObject("main").get("temp_min").toString();
                    minmaxView.setText(max+"\u00b0C - "+min+"\u00b0C");
                    explanationView.setText(((JSONObject)response.getJSONArray("weather").get(0)).get("description").toString());
                    humidityView.setText("Humidity: "+response.getJSONObject("main").get("humidity")+" %");
                    cloudView.setText("Cloud: "+response.getJSONObject("clouds").get("all").toString()+"%");
                }catch (JSONException ex){
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //DO NOTHING
            }
        });
        RequestQueueSingleton.getInstance(context).addRequestToQueue(openWeatherRequest);
    }
}