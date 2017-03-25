package com.example.masato.weatherforecast.model.Prefecture;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by masato on 17/03/22.
 */

public class Prefecture {

    @SerializedName("pref")
    @Expose
    private String pref;
    @SerializedName("city_names")
    @Expose
    private List<String> cityNames = null;
    @SerializedName("city_ids")
    @Expose
    private List<String> cityIds = null;

    public String getPref() {
        return pref;
    }

    public List<String> getCityNames() {
        return cityNames;
    }

    public List<String> getCityIds() {
        return cityIds;
    }

}
