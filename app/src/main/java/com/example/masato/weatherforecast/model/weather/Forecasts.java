package com.example.masato.weatherforecast.model.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by masato on 17/03/20.
 */

public class Forecasts {
    @Expose
    @SerializedName("dateLabel")
    private String dataLabel;

    @Expose
    @SerializedName("telop")
    private String telop;

    @Expose
    @SerializedName("date")
    private String date;

    @Expose
    @SerializedName("image")
    private Image image;

    @Expose
    @SerializedName("temerature")
    private Temperature temperature;

    public String getDataLabel() {
        return dataLabel;
    }

    public String getTelop() {
        return telop;
    }

    public String getDate() {
        return date;
    }

    public Image getImage() {
        return image;
    }

    public Temperature getTemperature() {
        return temperature;
    }

}
