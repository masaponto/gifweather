package com.example.masato.weatherforecast.model.giphy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by masato on 17/03/25.
 */

public class GiphyEntity {
    @SerializedName("data")
    @Expose
    private List<GiphyData> data;

    @SerializedName("pagination")
    @Expose
    private Pagination pagination;

    @SerializedName("meta")
    @Expose
    private MetaInfo meta;


    public List<GiphyData> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public MetaInfo getMeta() {
        return meta;
    }
}
