package com.example.masato.weatherforecast.model;

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

    public void setDataLabel(String dataLabel) {
        this.dataLabel = dataLabel;
    }

    public String getTelop() {
        return telop;
    }

    public void setTelop(String telop) {
        this.telop = telop;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }
}
