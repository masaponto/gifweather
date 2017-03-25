package com.example.masato.weatherforecast.model.flickr;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by masato on 17/03/25.
 */

public class FlickrPhotoEntity {
    @SerializedName("photos")
    @Expose
    private Photos photos;

    @SerializedName("stat")
    @Expose
    private String stat;

    public Photos getPhotos() {
        return photos;
    }

    public String getStat() {
        return stat;
    }
}
