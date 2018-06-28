package com.unizg.foi.GetInformations.constants;

public enum PlaceType
{
    AIRPORT("Airport"),
    AMUSEMENT_PARK("Amusement park"),
    ART_GALLERY("Art gallery"),
    BAKERY("Bakery"),
    BANK("Bank"),
    BOOK_STORE("Book store"),
    BUS_STATION("Bus station"),
    CAFE("Cafe"),
    CAR_WASH("Car wash"),
    CHURCH("Church"),
    CLOTHING_STORE("Clothing store"),
    DOCTOR("Doctor"),
    ELECTRONICS_STORE("Electronics store"),
    FIRE_STATION("Fire station"),
    GAS_STATION("Gas station"),
    GYM("Gym"),
    HOME_GOODS_STORE("Home goods store"),
    HOSPITAL("Hospital"),
    LAUNDRY("Laundry"),
    LIBRARY("Library"),
    MOVIE_THEATER("Movie theater"),
    MUSEUM("Museum"),
    NIGHT_CLUB("Night club"),
    PARK("Park"),
    PARKING("Parking"),
    PET_STORE("Pet store"),
    PHARMACY("Pharmacy"),
    POLICE("Police"),
    POST_OFFICE("Post office"),
    RESTAURANT("Restaurant"),
    SCHOOL("School"),
    SHOE_STORE("Shoe store"),
    SHOPPING_MALL("Shopping mall"),
    STADIUM("Stadium"),
    SUPERMARKET("Supermarket"),
    TRAIN_STATION("Train station"),
    VETERINARY_CARE("Veterinary care"),
    ZOO("Zoo");

    private String placeType;

    PlaceType(String placeType)
    {
        this.placeType = placeType;
    }

    public String getPlaceType()
    {
        return this.placeType;
    }
}
