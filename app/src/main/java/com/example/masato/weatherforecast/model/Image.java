package com.example.masato.weatherforecast.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by masato on 17/03/20.
 */

public class Image {
    @Expose
    @SerializedName("url")
    private String url;

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("width")
    private int width;

    @Expose
    @SerializedName("height")
    private int height;

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
