package com.unizg.foi.GetInformations.services.impl;

import com.unizg.foi.GetInformations.constants.ApiKeys;
import com.unizg.foi.GetInformations.model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.unizg.foi.GetInformations.services.PlaceService;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service("placeService")
public class PlaceServiceImpl implements PlaceService
{
    private static final String GOOGLE_PLACE_SEARCH_API_BASE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
    private static final String KEY_PARAM = "key";
    private static final String LOCATION_PARAM = "location";
    private static final String RADIUS_PARAM = "radius";
    private static final String TYPE_PARAM = "type";
    private static final String NEXT_PAGE_TOKEN = "pagetoken";

    private static final String GOOGLE_PLACE_DETAILS_API_BASE_URL = "https://maps.googleapis.com/maps/api/place/details/json";
    private static final String PLACE_ID = "placeid";

    @Override
    public PlaceSearchModel getPlacesByCategoryInRange(LocationModel place, String category, Integer distance)
    {
        PlaceSearchModel searchModel = new PlaceSearchModel();
        List<PlaceModel> placeList = new ArrayList<PlaceModel>();

        String location = place.getLatitude().toString().concat(",").concat(place.getLongitude().toString());
        UriComponentsBuilder builder = UriComponentsBuilder
            .fromUriString(GOOGLE_PLACE_SEARCH_API_BASE_URL)
            .queryParam(KEY_PARAM, ApiKeys.GOOGLE_API_KEY)
            .queryParam(LOCATION_PARAM, location)
            .queryParam(TYPE_PARAM, category)
            .queryParam(RADIUS_PARAM, distance);

        RestTemplate request = new RestTemplate();
        String result = request.getForObject(builder.toUriString(), String.class);

        JSONObject jsonObject = new JSONObject(result);

        if(jsonObject.has("next_page_token"))
        {
            searchModel.setNext_page_token(jsonObject.getString("next_page_token"));
        }

        if(StringUtils.equals(jsonObject.getString("status"), "OK"))
        {
            JSONArray results = jsonObject.getJSONArray("results");

            for(int i = 0; i < results.length(); i++)
            {
                PlaceModel placeModel = new PlaceModel();
                placeModel.setName(results.getJSONObject(i).getString("name"));
                placeModel.setPlaceId(results.getJSONObject(i).getString("place_id"));
                placeModel.setAddress(results.getJSONObject(i).getString("vicinity"));

                placeList.add(placeModel);
            }
        }

        searchModel.setPlaces(placeList);

        return searchModel;
    }

    @Override
    public PlaceSearchModel getPlacesByCategoryInRange(String next_page_token)
    {
        PlaceSearchModel searchModel = new PlaceSearchModel();
        List<PlaceModel> placeList = new ArrayList<PlaceModel>();

        UriComponentsBuilder builder = UriComponentsBuilder
            .fromUriString(GOOGLE_PLACE_SEARCH_API_BASE_URL)
            .queryParam(KEY_PARAM, ApiKeys.GOOGLE_API_KEY)
            .queryParam(NEXT_PAGE_TOKEN, next_page_token);

        RestTemplate request = new RestTemplate();
        String result = request.getForObject(builder.toUriString(), String.class);

        JSONObject jsonObject = new JSONObject(result);

        if(jsonObject.has("next_page_token"))
        {
            searchModel.setNext_page_token(jsonObject.getString("next_page_token"));
        }

        if(StringUtils.equals(jsonObject.getString("status"), "OK"))
        {
            JSONArray results = jsonObject.getJSONArray("results");

            for(int i = 0; i < results.length(); i++)
            {
                PlaceModel placeModel = new PlaceModel();
                placeModel.setName(results.getJSONObject(i).getString("name"));
                placeModel.setPlaceId(results.getJSONObject(i).getString("place_id"));
                placeModel.setAddress(results.getJSONObject(i).getString("vicinity"));

                placeList.add(placeModel);
            }
        }

        searchModel.setPlaces(placeList);

        return searchModel;
    }

    @Override
    public PlaceDetailsModel getPlaceDetails(String placeID)
    {
        PlaceDetailsModel place = new PlaceDetailsModel();

        UriComponentsBuilder builder = UriComponentsBuilder
            .fromUriString(GOOGLE_PLACE_DETAILS_API_BASE_URL)
            .queryParam(KEY_PARAM, ApiKeys.GOOGLE_API_KEY)
            .queryParam(PLACE_ID, placeID);

        RestTemplate request = new RestTemplate();
        String result = request.getForObject(builder.toUriString(), String.class);

        JSONObject jsonObject = new JSONObject(result);

        if(StringUtils.equals(jsonObject.getString("status"), "OK"))
        {
            place.setAddress(jsonObject.getJSONObject("result").getString("formatted_address"));
            place.setName(jsonObject.getJSONObject("result").getString("name"));

            if(jsonObject.getJSONObject("result").has("international_phone_number"))
            {
                place.setPhoneNumber(jsonObject.getJSONObject("result").getString("international_phone_number"));
            }

            place.setPlaceID(jsonObject.getJSONObject("result").getString("place_id"));

            LocationModel location = new LocationModel();
            location.setLatitude(jsonObject.getJSONObject("result").getJSONObject("geometry").getJSONObject("location").getBigDecimal("lat"));
            location.setLongitude(jsonObject.getJSONObject("result").getJSONObject("geometry").getJSONObject("location").getBigDecimal("lng"));
            place.setLocation(location);

            if(jsonObject.getJSONObject("result").has("rating"))
            {
                place.setRating(jsonObject.getJSONObject("result").getFloat("rating"));
            }

            if(jsonObject.getJSONObject("result").has("website"))
            {
                place.setWebsite(jsonObject.getJSONObject("result").getString("website"));
            }

            if(jsonObject.getJSONObject("result").has("opening_hours") &&
                    jsonObject.getJSONObject("result").getJSONObject("opening_hours").has("weekday_text"))
            {
                List<String> workingHours = new ArrayList<String>();

                JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONObject("opening_hours").getJSONArray("weekday_text");

                for(int i = 0; i < jsonArray.length(); i++)
                {
                    workingHours.add(jsonArray.getString(i));
                }

                place.setWorkingHours(workingHours);
            }

            if(jsonObject.getJSONObject("result").has("photos"))
            {
                List<PhotoModel> photos = new ArrayList<PhotoModel>();
                JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("photos");

                for(int i = 0; i < jsonArray.length(); i++)
                {
                    photos.add(new PhotoModel(jsonArray.getJSONObject(i).getString("photo_reference")));
                }

                place.setPhotos(photos);
            }
        }

        return place;
    }
}
