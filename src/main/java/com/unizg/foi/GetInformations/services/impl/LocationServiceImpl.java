package com.unizg.foi.GetInformations.services.impl;

import com.unizg.foi.GetInformations.constants.ApiKeys;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.thymeleaf.util.StringUtils;

import com.unizg.foi.GetInformations.model.LocationModel;
import com.unizg.foi.GetInformations.services.LocationService;

import java.io.UnsupportedEncodingException;

@Service("location")
public class LocationServiceImpl implements LocationService
{
    private static final String GOOGLE_API_BASE_URL = "https://maps.googleapis.com/maps/api/geocode/json";
    private static final String KEY_PARAM = "key";
    private static final String ADDRESS_PARAM = "address";

    private static final String RESULTS = "results";
    private static final String GEOMETRY = "geometry";
    private static final String LOCATION = "location";
    private static final String STATUS = "status";
    private static final String STATUS_OK = "OK";
    private static final String LATITUDE = "lat";
    private static final String LONGITUDE = "lng";

    @Override
    public LocationModel getLocationByAddress(String address) throws UnsupportedEncodingException
    {
        LocationModel location = new LocationModel();

        UriComponentsBuilder builder = UriComponentsBuilder
            .fromUriString(GOOGLE_API_BASE_URL)
            .queryParam(KEY_PARAM, ApiKeys.GOOGLE_API_KEY)
            .queryParam(ADDRESS_PARAM, address);

        RestTemplate request = new RestTemplate();
        String result = request.getForObject(builder.build(false).toUriString(), String.class);

        JSONObject jsonObject = new JSONObject(result);

        if(StringUtils.equals(jsonObject.getString(STATUS), STATUS_OK)){
            location.setLatitude(jsonObject.getJSONArray(RESULTS).getJSONObject(0).getJSONObject(GEOMETRY).getJSONObject(LOCATION).getBigDecimal(LATITUDE));
            location.setLongitude(jsonObject.getJSONArray(RESULTS).getJSONObject(0).getJSONObject(GEOMETRY).getJSONObject(LOCATION).getBigDecimal(LONGITUDE));
        }

        return location;
    }
}
