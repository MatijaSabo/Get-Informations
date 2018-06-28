package com.unizg.foi.GetInformations.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WeatherModel
{
    private String place;
    private Double temperature;
    private Double pressure;
    private Integer humidity;
    private String weather;
    private String weatherDescription;
    private String weatherIcon;
    private Double windSpeed;
    private Double rainQuantity;
    private Date weather_date;

    public WeatherModel() {
    }

    public String getPlace()
    {
        return this.place;
    }

    public void setPlace(String place)
    {
        this.place = place;
    }

    public Double getTemperature()
    {
        return this.temperature;
    }

    public void setTemperature(Double temperature)
    {
        this.temperature = temperature;
    }

    public Double getPressure()
    {
        return this.pressure;
    }

    public void setPressure(Double pressure)
    {
        this.pressure = pressure;
    }

    public Integer getHumidity()
    {
        return this.humidity;
    }

    public void setHumidity(Integer humidity)
    {
        this.humidity = humidity;
    }

    public String getWeather()
    {
        return this.weather;
    }

    public void setWeather(String weather)
    {
        this.weather = weather;
    }

    public String getWeatherDescription()
    {
        return this.weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription)
    {
        this.weatherDescription = weatherDescription;
    }

    public String getWeatherIcon() {
        String icon = this.weatherIcon.replaceAll("[^\\d]", "");
        return "/img/" + icon + "_2.png";
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public Double getWindSpeed()
    {
        return this.windSpeed;
    }

    public void setWindSpeed(Double windSpeed)
    {
        this.windSpeed = windSpeed;
    }

    public Double getRainQuantity()
    {
        return this.rainQuantity;
    }

    public void setRainQuantity(Double rainQuantity)
    {
        this.rainQuantity = rainQuantity;
    }

    public Date getWeather_date()
    {
        return this.weather_date;
    }

    public void setWeather_date(Date weather_date)
    {
        this.weather_date = weather_date;
    }

    public String getWeather_dateString() {
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dt1.format(this.weather_date);
    }
}
