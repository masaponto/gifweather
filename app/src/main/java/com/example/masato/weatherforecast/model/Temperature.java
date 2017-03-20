package com.example.masato.weatherforecast.model;

import com.android.annotations.Nullable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by masato on 17/03/20.
 */

public class Temperature {
    @Expose
    @Nullable
    @SerializedName("min")
    private Values min;

    @Expose
    @Nullable
    @SerializedName("max")
    private Values max;

    public Values getMin() {
        return min;
    }

    public void setMin(Values min) {
        this.min = min;
    }

    public Values getMax() {
        return max;
    }

    public void setMax(Values max) {
        this.max = max;
    }
}

