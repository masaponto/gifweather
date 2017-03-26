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
    private GiphyData data;

    @SerializedName("meta")
    @Expose
    private MetaInfo meta;


    public GiphyData getData() {
        return data;
    }

    public MetaInfo getMeta() {
        return meta;
    }
}
