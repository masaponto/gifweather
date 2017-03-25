package com.example.masato.weatherforecast.model.flickr;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by masato on 17/03/25.
 */

public class Photo {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("owner")
    @Expose
    private String owner;

    @SerializedName("secret")
    @Expose
    private String secret;

    @SerializedName("server")
    @Expose
    private String server;

    @SerializedName("farm")
    @Expose
    private int farm;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("ispublic")
    @Expose
    private int ispublic;

    @SerializedName("isfriend")
    @Expose
    private int isfriend;

    @SerializedName("isfamily")
    @Expose
    private int isfamily;

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getSecret() {
        return secret;
    }

    public String getServer() {
        return server;
    }

    public int getFarm() {
        return farm;
    }

    public String getTitle() {
        return title;
    }

    public int getIspublic() {
        return ispublic;
    }

    public int getIsfriend() {
        return isfriend;
    }

    public int getIsfamily() {
        return isfamily;
    }
}
