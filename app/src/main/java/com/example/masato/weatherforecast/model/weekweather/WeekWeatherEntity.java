package com.example.masato.weatherforecast.model.weekweather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by masato on 17/03/20.
 */

public class WeekWeatherEntity {
    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("feed")
    private Feed feed;

    @Expose
    @SerializedName("items")
    private List<Item> items;

    public String getStatus() {
        return status;
    }

    public Feed getFeed() {
        return feed;
    }

    public List<Item> getItems() {
        return items;
    }
}
