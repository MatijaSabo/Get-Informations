package com.unizg.foi.GetInformations.model;

import java.util.List;

public class PlaceSearchModel {
    private String next_page_token;
    private List<PlaceModel> places;

    public PlaceSearchModel() {
    }

    public String getNext_page_token() {
        return next_page_token;
    }

    public void setNext_page_token(String next_page_token) {
        this.next_page_token = next_page_token;
    }

    public List<PlaceModel> getPlaces() {
        return places;
    }

    public void setPlaces(List<PlaceModel> places) {
        this.places = places;
    }
}
