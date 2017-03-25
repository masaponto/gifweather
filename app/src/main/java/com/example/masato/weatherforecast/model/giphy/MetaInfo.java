package com.example.masato.weatherforecast.model.giphy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by masato on 17/03/25.
 */

public class MetaInfo {
    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("msg")
    @Expose
    private String msg;

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
