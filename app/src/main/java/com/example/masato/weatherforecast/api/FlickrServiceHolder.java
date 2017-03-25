package com.example.masato.weatherforecast.api;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by masato on 17/03/25.
 */

public class FlickrServiceHolder {

    private static FlickrService INSTANCE;

    public static FlickrService get () {
        if (INSTANCE == null) {
            INSTANCE = createInstance();
        }
        return INSTANCE;
    }

    private static FlickrService createInstance() {
        return retrofit().create(FlickrService.class);
    }

    private static Retrofit retrofit() {
        final String ENDPOINT = "https://api.flickr.com/";
        return new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}
