package com.unizg.foi.GetInformations.model;

import java.math.BigDecimal;

import org.json.JSONObject;

public class LocationModel
{
    private BigDecimal Latitude;
    private BigDecimal Longitude;

    public LocationModel() {
    }

    public BigDecimal getLatitude() {
        return this.Latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.Latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return this.Longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.Longitude = longitude;
    }

    @Override
    public String toString()
    {
        JSONObject json = new JSONObject();
        json.put("latitude", getLatitude());
        json.put("longitude", getLongitude());

        return json.toString();
    }
}
