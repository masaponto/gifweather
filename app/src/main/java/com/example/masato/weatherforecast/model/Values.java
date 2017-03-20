package com.example.masato.weatherforecast.model;

import com.android.annotations.Nullable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by masato on 17/03/20.
 */

public class Values {
    @Expose
    @SerializedName("celsius")
    private String celsius;

    @Expose
    @SerializedName("fahrenheit")
    private String fahrenheit;

    public String getCelsius() {
        return celsius;
    }

    public void setCelsius(String celsius) {
        this.celsius = celsius;
    }

    public String getFahrenheit() {
        return fahrenheit;
    }

    public void setFahrenheit(String fahrenheit) {
        this.fahrenheit = fahrenheit;
    }
}
