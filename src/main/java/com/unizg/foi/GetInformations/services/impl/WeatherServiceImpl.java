package com.unizg.foi.GetInformations.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.unizg.foi.GetInformations.model.WeatherModel;
import com.unizg.foi.GetInformations.model.LocationModel;
import com.unizg.foi.GetInformations.services.WeatherService;

import com.unizg.foi.GetInformations.constants.ApiKeys;

@Service("weatherService")
public class WeatherServiceImpl implements WeatherService
{
    private static final String CURRENT_WEATHER_DATA_API_BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String KEY_PARAM = "appid";
    private static final String LATITUDE_PARAM = "lat";
    private static final String LONGITUDE_PARAM = "lon";
    private static final String UNITS_PARAM = "units";
    private static final String UNITS_PARAM_VALUE = "metric";

    private static final String FIVE_DAYS_WEATHER_API_BASE_URL = "http://api.openweathermap.org/data/2.5/forecast";

    @Override
    public WeatherModel getCurrentWeather(LocationModel location) {
        WeatherModel weather = new WeatherModel();

        UriComponentsBuilder builder = UriComponentsBuilder
            .fromUriString(CURRENT_WEATHER_DATA_API_BASE_URL)
            .queryParam(KEY_PARAM, ApiKeys.OPENWEATHERMAP_APY_KEY)
            .queryParam(LATITUDE_PARAM, location.getLatitude())
            .queryParam(LONGITUDE_PARAM, location.getLongitude())
            .queryParam(UNITS_PARAM, UNITS_PARAM_VALUE);

        RestTemplate request = new RestTemplate();
        String result = request.getForObject(builder.toUriString(), String.class);

        JSONObject jsonObject = new JSONObject(result);

        weather.setPlace(jsonObject.getString("name"));
        weather.setTemperature(jsonObject.getJSONObject("main").getDouble("temp"));
        weather.setPressure(jsonObject.getJSONObject("main").getDouble("pressure"));
        weather.setHumidity(jsonObject.getJSONObject("main").getInt("humidity"));
        weather.setWeather(jsonObject.getJSONArray("weather").getJSONObject(0).getString("main"));
        weather.setWeatherDescription(jsonObject.getJSONArray("weather").getJSONObject(0).getString("description"));
        weather.setWeatherIcon(jsonObject.getJSONArray("weather").getJSONObject(0).getString("icon"));

        if(jsonObject.has("wind") && jsonObject.getJSONObject("wind").has("speed")){
            weather.setWindSpeed(jsonObject.getJSONObject("wind").getDouble("speed"));
        }

        if(jsonObject.has("rain") && jsonObject.getJSONObject("rain").has("3h")){
            weather.setRainQuantity(jsonObject.getJSONObject("rain").getDouble("3h"));
        }

        return weather;
    }

    @Override
    public List<WeatherModel> getFiveDaysWeather(LocationModel location) {
        List<WeatherModel> weatherList = new ArrayList<WeatherModel>();

        UriComponentsBuilder builder = UriComponentsBuilder
            .fromUriString(FIVE_DAYS_WEATHER_API_BASE_URL)
            .queryParam(KEY_PARAM, ApiKeys.OPENWEATHERMAP_APY_KEY)
            .queryParam(LATITUDE_PARAM, location.getLatitude())
            .queryParam(LONGITUDE_PARAM, location.getLongitude())
            .queryParam(UNITS_PARAM, UNITS_PARAM_VALUE);

        RestTemplate request = new RestTemplate();
        String result = request.getForObject(builder.toUriString(), String.class);

        JSONObject jsonObject = new JSONObject(result);
        JSONArray jsonArray = jsonObject.getJSONArray("list");

        for(int i = 0; i < jsonArray.length(); i++){
            WeatherModel weather = new WeatherModel();

            weather.setPlace(jsonObject.getJSONObject("city").getString("name"));
            weather.setTemperature(jsonArray.getJSONObject(i).getJSONObject("main").getDouble("temp"));
            weather.setPressure(jsonArray.getJSONObject(i).getJSONObject("main").getDouble("pressure"));
            weather.setHumidity(jsonArray.getJSONObject(i).getJSONObject("main").getInt("humidity"));
            weather.setWeather(jsonArray.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("main"));
            weather.setWeatherDescription(jsonArray.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("description"));
            weather.setWeatherIcon(jsonArray.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("icon"));

            if(jsonArray.getJSONObject(i).has("wind") && jsonArray.getJSONObject(i).getJSONObject("wind").has("speed")){
                weather.setWindSpeed(jsonArray.getJSONObject(i).getJSONObject("wind").getDouble("speed"));
            }

            if(jsonArray.getJSONObject(i).has("rain") && jsonArray.getJSONObject(i).getJSONObject("rain").has("3h")){
                weather.setRainQuantity(jsonArray.getJSONObject(i).getJSONObject("rain").getDouble("3h"));
            }

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String stringDate = jsonArray.getJSONObject(i).getString("dt_txt");

            try {
                Date date = formatter.parse(stringDate);
                weather.setWeather_date(date);
            } catch (ParseException e) { }

            weatherList.add(weather);
        }

        return weatherList;
    }
}
