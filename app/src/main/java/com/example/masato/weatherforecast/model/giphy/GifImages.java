package com.example.masato.weatherforecast.model.giphy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by masato on 17/03/25.
 */

public class GifImages {
    @SerializedName("fixed_height_downsampled")
    @Expose
    private Image image;

    public Image getImage() {
        return image;
    }
}
