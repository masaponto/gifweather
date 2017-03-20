package com.example.masato.weatherforecast.model.weekweather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by masato on 17/03/20.
 */

public class Item {
    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("pubDate")
    private String pubDate;

    @Expose
    @SerializedName("link")
    private String link;

    @Expose
    @SerializedName("guid")
    private String guid;

    @Expose
    @SerializedName("author")
    private String author;

    @Expose
    @SerializedName("thumbnail")
    private String thumbnail;

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("content")
    private String content;

    @Expose
    @SerializedName("enclosure")
    private List<Object> enclosure;

    @Expose
    @SerializedName("categories")
    private List<String> categories;

    public String getTitle() {
        return title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getLink() {
        return link;
    }

    public String getGuid() {
        return guid;
    }

    public String getAuthor() {
        return author;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public Object getEnclosure() {
        return enclosure;
    }

    public List<String> getCategories() {
        return categories;
    }
}
