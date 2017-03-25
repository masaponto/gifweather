package com.example.masato.weatherforecast.model.giphy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by masato on 17/03/25.
 */

public class Pagination {

    @SerializedName("total_count")
    @Expose
    private int total_count;

    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("offset")
    @Expose
    private int offset;

    public int getTotal_count() {
        return total_count;
    }

    public int getCount() {
        return count;
    }

    public int getOffset() {
        return offset;
    }
}
