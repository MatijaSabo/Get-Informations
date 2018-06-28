package com.unizg.foi.GetInformations.services;

import com.unizg.foi.GetInformations.model.PlaceDetailsModel;
import com.unizg.foi.GetInformations.model.LocationModel;
import com.unizg.foi.GetInformations.model.PlaceSearchModel;

public interface PlaceService
{
    PlaceSearchModel getPlacesByCategoryInRange(LocationModel place, String category, Integer distance);
    PlaceSearchModel getPlacesByCategoryInRange(String next_page_token);
    PlaceDetailsModel getPlaceDetails(String placeID);
}
