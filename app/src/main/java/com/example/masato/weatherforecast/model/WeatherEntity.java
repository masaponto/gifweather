package com.example.masato.weatherforecast.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by masato on 17/03/19.
 */

public class WeatherEntity {

    @Expose
    @SerializedName("pinpointLocations")
    private List<PinpointLocations> pinpointLocations;

    @Expose
    @SerializedName("forecasts")
    private List<Forecasts> forecasts;

    @Expose
    @SerializedName("link")
    private String link;

    public List<Forecasts> getForecasts() {
        return forecasts;
    }

    public String getLink() {
        return link;
    }

    public List<PinpointLocations> getPinpointLocations() {
        return pinpointLocations;
    }

}