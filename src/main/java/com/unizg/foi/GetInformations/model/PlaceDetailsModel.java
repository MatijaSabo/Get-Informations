package com.unizg.foi.GetInformations.model;

import java.util.List;

public class PlaceDetailsModel
{
    private String address;
    private String phoneNumber;
    private String name;
    private List<String> workingHours;
    private String placeID;
    private float rating;
    private String website;
    private LocationModel location;
    private List<PhotoModel> photos;

    public PlaceDetailsModel() {
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getWorkingHours() {
        return this.workingHours;
    }

    public void setWorkingHours(List<String> workingHours) {
        this.workingHours = workingHours;
    }

    public String getPlaceID() {
        return this.placeID;
    }

    public void setPlaceID(String placeID) {
        this.placeID = placeID;
    }

    public float getRating() {
        return this.rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public LocationModel getLocation() {
        return this.location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

    public List<PhotoModel> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoModel> photos) {
        this.photos = photos;
    }

    public void addPhoto(PhotoModel photo) {
        this.photos.add(photo);
    }
}
