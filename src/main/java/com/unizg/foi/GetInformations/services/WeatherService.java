package com.unizg.foi.GetInformations.services;

import java.util.List;

import com.unizg.foi.GetInformations.model.WeatherModel;
import com.unizg.foi.GetInformations.model.LocationModel;

public interface WeatherService
{
    WeatherModel getCurrentWeather(LocationModel location);
    List<WeatherModel> getFiveDaysWeather(LocationModel location);
}
