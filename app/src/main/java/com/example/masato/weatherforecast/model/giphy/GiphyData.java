package com.example.masato.weatherforecast.model.giphy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by masato on 17/03/25.
 */

public class GiphyData {
    @SerializedName("images")
    @Expose
    private GifImages images;

    public GifImages getImages() {
        return images;
    }
}




