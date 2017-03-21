package com.example.masato.weatherforecast.model.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by masato on 17/03/19.
 */

public class WeatherEntity {

    @Expose
    @SerializedName("forecasts")
    private List<Forecasts> forecasts;

    @Expose
    @SerializedName("description")
    private Descriptions description;

    public List<Forecasts> getForecasts() {
        return forecasts;
    }

    public Descriptions getDescription() {
        return description;
    }
}