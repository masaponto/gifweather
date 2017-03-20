package com.example.masato.weatherforecast.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by masato on 17/03/20.
 */


public class PinpointLocations {

    @Expose
    @SerializedName("link")
    private String link;

    @Expose
    @SerializedName("name")
    private String name;

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

}