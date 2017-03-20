package com.example.masato.weatherforecast.model.weekweather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by masato on 17/03/20.
 */

public class Feed {
    @Expose
    @SerializedName("url")
    private String url;

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("link")
    private String link;

    @Expose
    @SerializedName("author")
    private String author;

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("image")
    private String image;

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }
}
