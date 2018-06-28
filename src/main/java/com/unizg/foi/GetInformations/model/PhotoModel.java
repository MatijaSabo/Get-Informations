package com.unizg.foi.GetInformations.model;

import com.unizg.foi.GetInformations.constants.ApiKeys;
import org.springframework.web.util.UriComponentsBuilder;

public class PhotoModel {

    private static final String GOOGLE_PHOTOS_API_BASE_URL = "https://maps.googleapis.com/maps/api/place/photo";
    private static final String KEY_PARAM = "key";
    private static final String PHOTO_ID_PARAM = "photoreference";
    private static final String MAX_HEIGHT_PARAM = "maxheight";

    private String photoId;

    public PhotoModel(String photoId) {
        this.photoId = photoId;
    }

    @Override
    public String toString() {
        UriComponentsBuilder url = UriComponentsBuilder
                .fromUriString(GOOGLE_PHOTOS_API_BASE_URL)
                .queryParam(KEY_PARAM, ApiKeys.GOOGLE_API_KEY)
                .queryParam(PHOTO_ID_PARAM, this.photoId)
                .queryParam(MAX_HEIGHT_PARAM, 400);

        return url.toUriString();
    }
}
