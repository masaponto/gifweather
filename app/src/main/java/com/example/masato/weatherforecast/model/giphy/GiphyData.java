package com.example.masato.weatherforecast.model.giphy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by masato on 17/03/25.
 */

public class GiphyData {
    @SerializedName("fixed_height_downsampled_url")
    @Expose
    private String url;

    public String getUrl() {
        return url;
    }
}




