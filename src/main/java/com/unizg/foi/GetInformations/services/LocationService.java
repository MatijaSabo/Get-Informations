package com.unizg.foi.GetInformations.services;

import com.unizg.foi.GetInformations.model.LocationModel;

import java.io.UnsupportedEncodingException;

public interface LocationService
{
    LocationModel getLocationByAddress(String address) throws UnsupportedEncodingException;
}
