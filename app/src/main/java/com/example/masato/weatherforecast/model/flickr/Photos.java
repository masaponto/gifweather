package com.example.masato.weatherforecast.model.flickr;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by masato on 17/03/25.
 */

public class Photos {

    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("pages")
    @Expose
    private int pages;

    @SerializedName("perpage")
    @Expose
    private int perpage;

    @SerializedName("total")
    @Expose
    private String total;

    @SerializedName("photo")
    @Expose
    private List<Photo> photo;

    public int getPage() {
        return page;
    }

    public int getPages() {
        return pages;
    }

    public int getPerpage() {
        return perpage;
    }

    public String getTotal() {
        return total;
    }

    public List<Photo> getPhoto() {
        return photo;
    }
}
