package com.example.masato.weatherforecast.model.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by masato on 17/03/21.
 */

public class Descriptions {

    @Expose
    @SerializedName("text")
    private String text;

    @Expose
    @SerializedName("publicTime")
    private String publicTime;

    public String getText() {
        return text;
    }

    public String getPublicTime() {
        return publicTime;
    }
}
